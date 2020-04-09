package com.brightstar.plibmobi.ws.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.brightstar.plibmobi.config.Translator;
import com.brightstar.plibmobi.convert.SourcesDtoConverter;
import com.brightstar.plibmobi.exceptionhandler.BusinessException;
import com.brightstar.plibmobi.model.InterfaceConfig;
import com.brightstar.plibmobi.model.InterfacePoOrderDetails;
import com.brightstar.plibmobi.model.InterfaceRequest;
import com.brightstar.plibmobi.model.ServiceOrder;
import com.brightstar.plibmobi.model.ServiceOrderBbeMpx;
import com.brightstar.plibmobi.model.Sources;
import com.brightstar.plibmobi.model.TransactionUnit;
import com.brightstar.plibmobi.model.TransferDepositAx;
import com.brightstar.plibmobi.model.dto.SourcesDto;
import com.brightstar.plibmobi.repository.InterfaceConfigRepository;
import com.brightstar.plibmobi.repository.InterfacePoOrderRepository;
import com.brightstar.plibmobi.repository.InterfaceRequestRepository;
import com.brightstar.plibmobi.repository.ServiceOrderBbeMpxRepository;
import com.brightstar.plibmobi.repository.ServiceOrderRepository;
import com.brightstar.plibmobi.repository.SourcesRepository;
import com.brightstar.plibmobi.repository.TransactionUnitRepository;
import com.brightstar.plibmobi.repository.TransferDepositAxRepository;
import com.brightstar.plibmobi.util.InterfaceRequestFlow;
import com.brightstar.plibmobi.util.ProcessStep;
import com.brightstar.plibmobi.util.StringUtil;
import com.brightstar.plibmobi.util.TransactionMasterFlow;
import com.brightstar.plibmobi.util.XMLUtil;
import com.brightstar.plibmobi.ws.dto.ResultTransferAx;
import com.brightstar.plibmobi.ws.util.BuybackDepositType;
import com.brightstarcorp.erp.core.to.entity.inventSerialLastDimService.webservice.ax.erp.brightstarcorp.com.InventSerialLastDimServiceFindAifFaultFaultFaultMessage;
import com.brightstarcorp.erp.core.to.entity.inventTransferItemService.webservice.ax.erp.brightstarcorp.com.InventTransferItem;
import com.brightstarcorp.erp.core.to.entity.inventTransferItemService.webservice.ax.erp.brightstarcorp.com.InventTransferItemServiceInventTransferAifFaultFaultFaultMessage;
import com.brightstarcorp.erp.facade.InventSerialLastDimServiceFacade;
import com.brightstarcorp.erp.facade.InventTransferItemServiceFacade;
import com.microsoft.schemas.dynamics._2006._02.documents.entitykey.EntityKey;
import com.microsoft.schemas.dynamics._2006._02.documents.entitykey.KeyField;
import com.microsoft.schemas.dynamics._2006._02.documents.entitykeylist.EntityKeyList;

@Service
public class CustomerServiceAx {
	
	@Autowired
	private InterfaceConfigRepository interfaceConfigRep;
	
	@Autowired
	private TransactionUnitRepository transactionUnitRep;
	
	@Autowired
	private ServiceOrderRepository serviceOrderRep;
	
	@Autowired
	private ServiceOrderBbeMpxRepository serviceOrderBbeMpxRep;
	
	@Autowired
	private InterfacePoOrderRepository interfacePoOrderRep;
	
	@Autowired
	private InterfaceRequestRepository interfaceRequestRep;
	
	@Autowired
	private BuybackWebServiceFactory buybackWebServiceFactory;
	
	@Autowired
	private TransferDepositAxRepository transferDepositAxRep;
	
	@Autowired
	private SourcesRepository sourcesRepository;
	
	
	private String typeDefault;
	
	public void buybackProcessChangeDeposit(String jobId, String paramGroup, BuybackDepositType type) 
            throws Exception {
               
        Integer quantityUnits = null;
        
        // Busca no banco as informações necessárias para rodar o job.
        for (InterfaceConfig config : interfaceConfigRep.findByGroup(paramGroup)) {
            if (config.getName().equalsIgnoreCase("QUANTITY_UNITS")) {
                quantityUnits = Integer.parseInt(config.getValue());
            } 
        }

        
        Pageable pageable = PageRequest.of(0, quantityUnits, Sort.by(Sort.Direction.DESC, "receiveTimestamp"));
        Collection<TransactionUnit> units = transactionUnitRep.findByServiceOrderProcessStepAndMasterBrightstarSubsidiaryAndStatus(
        		ProcessStep.WAITING_TRANSFER_QUARANTINE_AX, 
        		TransactionMasterFlow.ROMANEIO_CAE_TO_BRIGHTSTAR,
        		"P",
        		pageable);
        
        List<Integer> orders = new ArrayList<Integer>();
        List<Integer> ordersOutPortfolio = new ArrayList<Integer>();
       
        
        for(TransactionUnit tu : units) {
            
            if(typeDefault == null) {
                typeDefault =  type.name();
            } else {
                try {
                    type = BuybackDepositType.valueOf(typeDefault);
                } catch(IllegalArgumentException ie) {
                    type = BuybackDepositType.QUARANTINE_DEPOSIT;
                }
            }
            
            ServiceOrder serviceOrder = tu.getServiceOrder();
            
            // Verifica se está fora de portfolio
            Integer outPortFolio = 0;
            Optional<ServiceOrderBbeMpx> optbbeMpx = serviceOrderBbeMpxRep.findOdsBbeMpxByServiceOrderIdAndItgType(serviceOrder.getId(), 1);
            if(optbbeMpx.isPresent()) {
            	outPortFolio = optbbeMpx.get().getOutportfolio();
            }
            
            if(outPortFolio != null && outPortFolio.intValue() == 1) {
                type = BuybackDepositType.SALE_DEPOSIT;
            }
            
            ResultTransferAx result = null;
            try {
                // WS de transferencia de deposito
                result = buybackIntregrationAx(serviceOrder.getId(), null, type, "JOB_TRANSFER_AX", true);
            } catch (Exception e) {
                result = new ResultTransferAx();
                result.setMessageError(e.getMessage().length() > 1000 ? StringUtil.substring(e.getMessage(), 0, 950) : e.getMessage() );
            }
            
            
            if(StringUtil.isNotBlank(result.getMessageError())) {
                tu.setErrorReason(result.getMessageError());
                tu.setStatus("E");
            } else {
                tu.setStatus("C");
                tu.setNotes(result.getField() + " - " + result.getValue());
                
                if(outPortFolio != null && outPortFolio.intValue() == 1) {
                    ordersOutPortfolio.add(serviceOrder.getId());
                } else {
                    orders.add(serviceOrder.getId());
                }
            }
            transactionUnitRep.save(tu);
        }
        
        // Fecha ODS caso esteja fora de portfolio
        if(ordersOutPortfolio.size() > 0) {
        	for(Integer serviceOrderId : ordersOutPortfolio) {
	        	serviceOrderRep.updateServiceOrder(
	        			ProcessStep.CLOSED.name(),
	        			"JOB_TRANSFER_AX",
	        			Calendar.getInstance().getTime(),
	        			"Closed for being out of portfolio.",
	        			serviceOrderId
	                    );
        	}
        }
        
        // Muda as ODS para "Tranferido AX de InTransit -> Quarentine"
        if(orders.size() > 0) {
        	for(Integer serviceOrderId : orders) {
	        	serviceOrderRep.updateServiceOrder(
	        			ProcessStep.TRANSFERRED_TO_QUARANTINE_AX.name(),
	        			"JOB_TRANSFER_AX",
	        			Calendar.getInstance().getTime(),
	        			null,
	        			serviceOrderId
	                    );
        	}
            
        }
        
    }
	
	
	
	 /**
     * Chamada do AX para a mudança de Deposito, utilizando o mesmo WS do BB Online.
     * 
     * @param serviceOrderId
     * @param typeMove
     * @throws Exception 
     */
    public ResultTransferAx buybackIntregrationAx(Integer serviceOrderId, String partNumber, BuybackDepositType typeMove,
    		String userName, boolean execJob) throws Exception {

        String company = "";
        String inventLocationId = "";
        String wmsLocationId = "";
        String endpoint = "";
        String endpointAddress = "";
        
        // Busca no banco as informações necessárias para rodar o job.
        List<InterfaceConfig> configs = interfaceConfigRep.findByGroup("BUYBACK_BBTI_CLIENT");
        
        for (InterfaceConfig config : configs) {
            if (config.getName().equalsIgnoreCase("COMPANY_AX")) {
                company = config.getValue();
            } else if (config.getName().equalsIgnoreCase(typeMove.name())) {
                inventLocationId = config.getValue();
            } else if (config.getName().equalsIgnoreCase(typeMove.getLocation())) {
                wmsLocationId = config.getValue();
            } else if (config.getName().equalsIgnoreCase("ENDPOINT")) {
                endpoint = config.getValue();
            } else if (config.getName().equalsIgnoreCase("ENDPOINT_ADDRESS")) {
                endpointAddress = config.getValue();
            }
        }
        
        
        Optional<ServiceOrder> optServiceOrder = serviceOrderRep.findById(serviceOrderId);
        if(!optServiceOrder.isPresent()) {
        	throw new Exception(Translator.getText("message.errorAx.ods", new String[] {serviceOrderId.toString()}));
        }
        ServiceOrder serviceOrder = optServiceOrder.get();
        
        List<InterfacePoOrderDetails> heads = interfacePoOrderRep.findByServiceOrderIdAndBbLineRefNbrBbRefNumberPoNumberIsNotNull(serviceOrder.getId());
        String poNumber = null;
            
        // Neste caso a TRANSFER_DEPOSIT_AX está sendo usada apenas para registra o pedido de transferencia 
        TransferDepositAx transfer = new TransferDepositAx();
        transfer.setServiceOrderId(serviceOrder.getId());
        transfer.setSerialNumber(serviceOrder.getSerialNumber());
        transfer.setInventLocationId(inventLocationId);
        transfer.setWmsLocationId(wmsLocationId);
        transfer.setPlibReference("BUYBACK_CHANGE_DEPOSIT");
        transfer.setTypeTransfer(typeMove.name());
        transfer.setCreateUserId(userName);
        
        
        ResultTransferAx resultTransferAx = new ResultTransferAx();
        
        if(heads.isEmpty()) {
        	transfer.setPonbr(partNumber);
        } else {
        	poNumber = heads.get(0).getBbLineRefNbr().getBbRefNumber().getPoNumber();
        	transfer.setPonbr(poNumber);
        }
        
        InventTransferItem param = new InventTransferItem();
        param.setInventSerialId(serviceOrder.getSerialNumber());
        param.setInventLocationIdNew(inventLocationId);
        param.setWmsLocationIdNew(wmsLocationId);            
        param.setNotSerialize(true);
        
        transfer.setRequest(XMLUtil.toXML(param));
        
        // Fazendo a mesma chamada do Online para a utilização no Fisico
        InventTransferItemServiceFacade client = buybackWebServiceFactory.getWSClientInventTransferItem();
        client.setEndpointAddress(endpoint+endpointAddress);
        
        // Registra a operação.
        InterfaceRequest interfaceRequest = new InterfaceRequest(
                serviceOrder.getCountry()
                ,XMLUtil.toXML(param)
                ,poNumber!=null?poNumber:partNumber
                ,InterfaceRequestFlow.OUTBOUND
                ,"BUYBACK_CHANGE_DEPOSIT" + ": "+typeMove.name()
                ,null);
        
        interfaceRequestRep.save(interfaceRequest);
        
        KeyField result;
        try {
            result = client.create(company, param);
            
            resultTransferAx.setField(result.getField());
            resultTransferAx.setValue(result.getValue());
            
            transfer.setStatus("C");
            
        } catch (InventTransferItemServiceInventTransferAifFaultFaultFaultMessage err) {
            transfer.setStatus("E");
            transfer.setErrorDescription(err.getMessage());
            
            resultTransferAx.setMessageError(err.getMessage());
            
            if(!execJob) {
                throw new Exception(Translator.getText("message.purchase.error.ax.unexpected", new String[] {err.getMessage()}));
            }
        }
        
        transferDepositAxRep.save(transfer);
        
        return resultTransferAx;
    }
    
    
    
    public SourcesDto findDimension(String serialNumber) throws BusinessException, InventSerialLastDimServiceFindAifFaultFaultFaultMessage {
        
        String company = "";
        String endpoint = "";
        String endpointAddress = "";
        SourcesDto sourcesDto = null;
        SourcesDto result = new SourcesDto();
        
        try {
            // Busca no banco as informações necessárias para rodar o job.
        	List<InterfaceConfig> configs = interfaceConfigRep.findByGroup("BUYBACK_BBTI_DIMENSION");
            for (InterfaceConfig config : configs) {
                if (config.getName().equalsIgnoreCase("COMPANY_AX")) {
                    company = config.getValue();
                } else if (config.getName().equalsIgnoreCase("ENDPOINT")) {
                    endpoint = config.getValue();
                } else if (config.getName().equalsIgnoreCase("ENDPOINT_ADDRESS")) {
                    endpointAddress = config.getValue();
                } 
            }
    
            EntityKeyList entityKeyList = null;
            
            InventSerialLastDimServiceFacade client = buybackWebServiceFactory.getWSClientInventSerialLastDim();
            client.setEndpointAddress(endpoint+endpointAddress);
            
            entityKeyList = client.find(company, serialNumber);
            
    
            SourcesDto parans = new SourcesDto();
            
            
            if(entityKeyList!= null && entityKeyList.getEntityKey().size() > 0) {
                
                for(EntityKey entityKey : entityKeyList.getEntityKey()) {
                    for(KeyField keyField : entityKey.getKeyData().getKeyField()) {
                        
                        if("wMSLocationId".equalsIgnoreCase(keyField.getField())) { // Localização
                            parans.setLocation(keyField.getValue());
                        } else if("InventLocationId".equalsIgnoreCase(keyField.getField())) { // Depósito
                            parans.setDeposit(keyField.getValue());
                        } else if("ItemName".equalsIgnoreCase(keyField.getField())) { // Nome do Modelo
                            parans.setModelName(keyField.getValue());
                        } else if("ItemId".equalsIgnoreCase(keyField.getField())) { // Part Number
                            parans.setPartNumber(keyField.getValue());
                        }
                    }
                }
                
                if(StringUtil.isBlank(parans.getLocation())){
                    result.addMsgError(Translator.getText("message.purchase.error.ax.location"));
                    return result;
                }
                
                if(StringUtil.isBlank(parans.getDeposit())){
                    result.addMsgError(Translator.getText("message.purchase.error.ax.deposit"));
                    return result;
                }
                
                Sources sources = sourcesRepository.findByLocationAndDeposit(
                        parans.getLocation(), 
                        parans.getDeposit());
                
                sourcesDto = new SourcesDtoConverter().convert(sources);
                
                
                if(sourcesDto == null) {
                    result.addMsgError(Translator.getText("message.errorAx.dimension", new String[] {serialNumber}));
                    return result;
                } else {
                	sourcesDto.setPartNumber(parans.getPartNumber());
                	sourcesDto.setModelName(parans.getModelName());
                }
                
            } else {
                result.addMsgError(Translator.getText("message.purchase.error.ax.serialEmpty", new String[] {serialNumber}));
                return result;
            }
                 
        } catch(InventSerialLastDimServiceFindAifFaultFaultFaultMessage e) {
            result.addMsgError(Translator.getText("message.purchase.error.ax.ws", new String[] {(e.getMessage()!=null?e.getMessage():e.getFaultInfo().toString())}));
            return result;
        }
        
        return sourcesDto;
    }
    

}
