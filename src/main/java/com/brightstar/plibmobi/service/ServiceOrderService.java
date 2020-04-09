package com.brightstar.plibmobi.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightstar.plibmobi.config.Translator;
import com.brightstar.plibmobi.convert.ServiceOrderDtoConverter;
import com.brightstar.plibmobi.model.InterfaceConfig;
import com.brightstar.plibmobi.model.Provider;
import com.brightstar.plibmobi.model.ReceivedTransporter.ReceivedTransporterType;
import com.brightstar.plibmobi.model.ServiceOrder;
import com.brightstar.plibmobi.model.ServiceOrderBbeMpx;
import com.brightstar.plibmobi.model.TransactionMaster;
import com.brightstar.plibmobi.model.TransactionUnit;
import com.brightstar.plibmobi.model.dto.ReceivedTransporterDto;
import com.brightstar.plibmobi.model.dto.ServiceOrderDto;
import com.brightstar.plibmobi.model.view.ReceiveForm;
import com.brightstar.plibmobi.model.view.ReceiveView;
import com.brightstar.plibmobi.repository.InterfaceConfigRepository;
import com.brightstar.plibmobi.repository.ProviderRepository;
import com.brightstar.plibmobi.repository.ServiceOrderRepository;
import com.brightstar.plibmobi.repository.TransactionMasterRepository;
import com.brightstar.plibmobi.util.DateUtil;
import com.brightstar.plibmobi.util.ProcessStep;
import com.brightstar.plibmobi.util.TransactionMasterFlow;

/**
 * 
 * @author HB16131
 *
 */
@Service
public class ServiceOrderService {
	
	private Integer diffDateDay = 0;
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private InterfaceConfigRepository interfaceConfigRepository;
	
	@Autowired
	private ProviderRepository providerRepository;
	
	@Autowired
	private TransactionMasterRepository transactionMasterRepository;
	
	@Autowired
	private ReceiveTransporterService receiveTransporterService;
	

	public Optional<ServiceOrder> getServiceOrder(Integer id) {
		return serviceOrderRepository.findById(id);
	}
	
	public Optional<List<ServiceOrder>> findServiceOrderByImei(String serialNumber) {
		return serviceOrderRepository.findBySerialNumber(serialNumber);
	}
	
	public ServiceOrderDto findServicOrderByImeiNotClosedAndCancel(String serialNumber) {
		
		// Steps para exclusao
		Collection<ProcessStep> steps = ProcessStep.convert(
				Arrays.asList(new String[] {ProcessStep.CLOSED.name(), ProcessStep.CANCELED.name()}));
		
		
		Optional<ServiceOrder> optServiceOrder = serviceOrderRepository.findOdsBySerialNumberAndProcessStepNotIn(serialNumber, steps);
		
		
		if(!optServiceOrder.isPresent()) {
			return null; 
		}
		
		ServiceOrderDto serviceOrderDto = new ServiceOrderDtoConverter().convert(optServiceOrder.get());
		
		return serviceOrderDto;
		
	}
	
	
	
	/**
	 * Validação do Serial no recebimento
	 * 
	 * @param serial - Serial imputado na tela
	 * @param damage - Selecao do tipo de recebimento
	 * @return
	 */
	public ReceiveView validateSlaBySerialNumber(String serial, boolean damage) {
		
		ReceiveView view = null;
		Optional<ServiceOrder> optOrder = serviceOrderRepository.findOdsBySerialNumberAndProcessStep(
				serial, ProcessStep.SHIPPED_TO_BRIGHTSTAR);
		
		
		if(optOrder.isPresent()) {
			
			view = new ReceiveView();
			
			ServiceOrder serviceOrder = optOrder.get();
			ServiceOrderDto serviceOrderDto = new ServiceOrderDtoConverter().convert(serviceOrder);
			
			// Definição do SLA
			setDiffDateDay(DateUtil.countDayBetween(serviceOrder.getUpdateTimestamp(), new Date()));
			
			if(damage) {
				// Verifica o registro de discrepancia no transportador
				Optional<InterfaceConfig> optInterfaceConfig = interfaceConfigRepository.findByGroupAndName("BUYBACK_TIER_BBE", "UNDAMAGE");
				if(optInterfaceConfig.isPresent()) {
					InterfaceConfig interfaceConfig = optInterfaceConfig.get();
					if(serviceOrderDto.getGrandingInit().equals(interfaceConfig.getValue())) {
						view.setDamage(1);
					} else {
						view.setDamage(0);
					}
				}
			} else {
				view.setDamage(0);
			}
			
			view.setSla(getDiffDateDay());
			view.setValidAccessKey(1);
			view.setServiceOrderDto(serviceOrderDto);
			
		} else {
			view = new ReceiveView(Translator.getText("message.received.nonexistentItem", new String[] {serial}), 0, null);
		}
		
		return view;
	}
	
	public Integer saveReceived(ReceiveForm receiveForm, String userName, Date atualDate) {
		
		String imeis[] = receiveForm.getSerials().split("-");
		
		TransactionMaster transactionMaster = new TransactionMaster();
		transactionMaster.setBrightstarSubsidiary(TransactionMasterFlow.ROMANEIO_CAE_TO_BRIGHTSTAR);
		transactionMaster.setCreateUserId(userName);
		transactionMaster.setCreateTimestamp(atualDate);
		transactionMaster.setUpdateUserId(userName);
		transactionMaster.setUpdateTimestamp(atualDate);
		transactionMaster.setEnabled("1");
		transactionMaster.setMasterBox("1");
		transactionMaster.setStatus("1");
		
		Optional<Provider> optProvider = providerRepository.findById(Integer.parseInt(receiveForm.getProvider()));
		if(optProvider.isPresent()) {
			transactionMaster.setProvider(optProvider.get());
		}
		
		
		ReceivedTransporterDto transporter = new ReceivedTransporterDto();
		
		for(String imei : imeis) {
			Optional<ServiceOrder> optOds = serviceOrderRepository.findOdsBySerialNumberAndProcessStep(
					imei.split(":")[0], ProcessStep.SHIPPED_TO_BRIGHTSTAR);
			
			if(optOds.isPresent()) {
			
				ServiceOrder serviceOrder = optOds.get();
				
				serviceOrder.setProcessStep(ProcessStep.BRIGHTSTAR_RECEIVED_FROM_CAE);
				serviceOrder.setUpdateUserId(userName);
				serviceOrder.setUpdateTimestamp(atualDate);
				serviceOrderRepository.save(serviceOrder);
				
				
				// Adiciona 10 seg.
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(atualDate);
				calendar.add(Calendar.SECOND, 10);
				
				serviceOrder.setProcessStep(ProcessStep.WAITING_TRANSFER_QUARANTINE_AX);
				serviceOrder.setUpdateUserId(userName);
				serviceOrder.setUpdateTimestamp(calendar.getTime());
								
				TransactionUnit transactionUnit = new TransactionUnit();
				
				transactionUnit.setServiceOrder(optOds.get());
				transactionUnit.setCreateUserId(userName);
				transactionUnit.setCreateTimestamp(atualDate);
				transactionUnit.setUpdateUserId(userName);
				transactionUnit.setUpdateTimestamp(atualDate);
				transactionUnit.setReceiveUserId(userName);
				transactionUnit.setReceiveTimestamp(atualDate);
				transactionUnit.setStatus("P");
				
				transactionUnit.setMaster(transactionMaster);
				transactionMaster.addUnits(transactionUnit);
				
				
				ServiceOrderBbeMpx orderBbeMpx = serviceOrder.getOrderBbeMpx().size() > 0 
						? serviceOrder.getOrderBbeMpx().get(0) : null;
				
				if(orderBbeMpx != null) {
					transporter.addParam(
							serviceOrder.getId(), 
							orderBbeMpx.getGrandingInit(), 
							imei.split(":")[1]);
				}
				
			}
			
		}
		
		transactionMasterRepository.save(transactionMaster);
		
		
		
		Optional<InterfaceConfig> optInterfaceConfig = interfaceConfigRepository.findByGroupAndName("BUYBACK_TIER_BBE", "UNDAMAGE");
		
		transporter.setUser(userName);
		transporter.setCreateDate(atualDate);
		transporter.setProviderId(Integer.parseInt(receiveForm.getProvider()));
		
		if(optInterfaceConfig.isPresent()) {
			transporter.setTierDefault(optInterfaceConfig.get().getValue());
		}
		        
		transporter.setType(ReceivedTransporterType.findTypeBySelected(new Boolean(receiveForm.getType()) ? 2 : 1));
		
		receiveTransporterService.save(transporter);
		
		
		
		return (transactionMaster.getTransctionMasterId() != null && transactionMaster.getTransctionMasterId().intValue() > 0) 
				? transactionMaster.getTransctionMasterId() : 0;
	}
	
	
	public Integer getDiffDateDay() {
		return diffDateDay;
	}

	public void setDiffDateDay(Integer diffDateDay) {
		this.diffDateDay = diffDateDay;
	}
	
}
