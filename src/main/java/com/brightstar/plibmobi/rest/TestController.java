package com.brightstar.plibmobi.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brightstar.mpx.client.PricingMatrixQuoteOutput;
import com.brightstar.plibmobi.model.Carrier;
import com.brightstar.plibmobi.model.Checklist;
import com.brightstar.plibmobi.model.ChecklistGroup;
import com.brightstar.plibmobi.model.FromToChecklistType;
import com.brightstar.plibmobi.model.InterfacePoOrderDetails;
import com.brightstar.plibmobi.model.InterfaceRequest;
import com.brightstar.plibmobi.model.InterfaceXref;
import com.brightstar.plibmobi.model.Model;
import com.brightstar.plibmobi.model.ModelPartNumber;
import com.brightstar.plibmobi.model.Provider;
import com.brightstar.plibmobi.model.ReceivedTransporter;
import com.brightstar.plibmobi.model.ServiceOrder;
import com.brightstar.plibmobi.model.ServiceOrderBbeMpx;
import com.brightstar.plibmobi.model.ServiceOrderBuyback;
import com.brightstar.plibmobi.model.TransactionMaster;
import com.brightstar.plibmobi.model.TransactionUnit;
import com.brightstar.plibmobi.model.view.ReceiveView;
import com.brightstar.plibmobi.model.view.RequestReceived;
import com.brightstar.plibmobi.repository.ChecklistGroupRepository;
import com.brightstar.plibmobi.repository.FromToChecklistTypeRepository;
import com.brightstar.plibmobi.repository.InterfacePoOrderRepository;
import com.brightstar.plibmobi.repository.InterfaceRequestRepository;
import com.brightstar.plibmobi.repository.InterfaceXrefRepository;
import com.brightstar.plibmobi.repository.ModelPartNumberRepository;
import com.brightstar.plibmobi.repository.ProviderRepository;
import com.brightstar.plibmobi.repository.ServiceOrderBbeMpxRepository;
import com.brightstar.plibmobi.repository.ServiceOrderBuybackRepository;
import com.brightstar.plibmobi.service.ChecklistService;
import com.brightstar.plibmobi.service.ServiceOrderService;
import com.brightstar.plibmobi.service.TransactionMasterService;
import com.brightstar.plibmobi.service.UtilsService;
import com.brightstar.plibmobi.ws.BackendBuybackMpxServiceFacade;
import com.brightstar.plibmobi.ws.InterfaceBuybackService;
import com.brightstar.plibmobi.ws.dto.ResultTransferAx;
import com.brightstar.plibmobi.ws.services.CustomerServiceAx;
import com.brightstar.plibmobi.ws.util.BuybackDepositType;


@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private UtilsService utilsService;
	
	@Autowired
	private TransactionMasterService transactionMasterService;
	
	@Autowired
	private ServiceOrderBbeMpxRepository serviceOrderBbeMpxRepository;
	
	@Autowired
	private ServiceOrderBuybackRepository serviceOrderBuybackRepository;
	
	@Autowired
	private ServiceOrderService srviceOrderService;
	
	@Autowired
	private ProviderRepository providerRepository;
	
	@Autowired
	private BackendBuybackMpxServiceFacade facade;
	
	@Autowired
	private InterfaceBuybackService interfaceBuybackService;
	
	@Autowired
	private ChecklistGroupRepository checklistGroupRepository;
	
	@Autowired
	private InterfaceRequestRepository interfaceRequestRepository;
	
	@Autowired
	private ModelPartNumberRepository modelPartNumberRepository;
	
	@Autowired
	private InterfaceXrefRepository interfaceXrefRepository;
	
	@Autowired
	private InterfacePoOrderRepository interfacePoOrderRep;
	
	@Autowired
	private CustomerServiceAx customerServiceAx;
	
	@Autowired
	private ChecklistService checklistService;
	
	@Autowired
	private FromToChecklistTypeRepository fromToChecklistTypeRepository;
	
	
	@RequestMapping(value = "/models/{carrierId}", method = RequestMethod.GET)
	public List<Model> findModelByCarrierId(@PathVariable Integer carrierId) {
		return utilsService.findModelByCarrierCarrierId(carrierId, "1");
	}
	
	@RequestMapping(value = "/fromTo/{ids}", method = RequestMethod.GET)
	public List<FromToChecklistType> findFromTo(@PathVariable String ids) {
		
		List<Integer> lst = Arrays.asList(ids.split("-")).stream()
				  .map(s -> Integer.parseInt(s))
				  .collect(Collectors.toList());

		List<FromToChecklistType> list = fromToChecklistTypeRepository.findByChecklistTypeIdFromIdIn(lst);
		return list;
	}
	
	
	@RequestMapping(value = "/model/{id}", method = RequestMethod.GET)
	public Optional<Model> findModelById(@PathVariable Integer id) {
		return utilsService.findModelById(id);
	}
	
	
	@RequestMapping(value = "/questionsByCountry/{countryId}", method = RequestMethod.GET)
	public List<Checklist> findCheckListGroup(@PathVariable Integer countryId) {
		return checklistService.findQuestionsByCountryId(countryId);
	}
	
	
	/** ============== Teste de Relacionamento */
	
			
	@RequestMapping(value = "/ods/{id}", method = RequestMethod.GET)
	public ResponseEntity<ServiceOrder> getUsers(@PathVariable Integer id) {
		
		Optional<ServiceOrder> serviceOrderOpt = srviceOrderService.getServiceOrder(id);
		
		if(!serviceOrderOpt.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(serviceOrderOpt.get());
	}
	
	@RequestMapping(value = "/ods/findSerial/{serialNumber}", method = RequestMethod.GET)
	public ResponseEntity<List<ServiceOrder>> findServiceOrderByImei(@PathVariable String serialNumber) {
		
		Optional<List<ServiceOrder>> optServiceOrder = srviceOrderService.findServiceOrderByImei(serialNumber);
		
		if(!optServiceOrder.isPresent()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(optServiceOrder.get());
	}
	
	@RequestMapping(value = "/masterbox/{id}", method = RequestMethod.GET)
	public ResponseEntity<TransactionMaster> getMasterBox(@PathVariable Integer id) {
		
		Optional<TransactionMaster> masterBoxOpt = transactionMasterService.findMasterBox(id);
		
		if(!masterBoxOpt.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(masterBoxOpt.get());
	}
	
	@RequestMapping(value = "/provider/{id}", method = RequestMethod.GET)
	public ResponseEntity<Provider> getProvider(@PathVariable Integer id) {
		
		Optional<Provider> provider = providerRepository.findById(id);
		
		if(!provider.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(provider.get());
	}

	
	@RequestMapping(value = "/receivedTransport/{id}", method = RequestMethod.GET)
	public ResponseEntity<ReceivedTransporter> getReceivedTransport(@PathVariable Integer id) {
		
		Optional<ReceivedTransporter> receivedTransporterOpt = utilsService.findReceivedTransport(id);
		
		if(!receivedTransporterOpt.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(receivedTransporterOpt.get());
	}
	
	@RequestMapping(value = "/odsBbeMpx/{serviceOrderId}", method = RequestMethod.GET)
	public ResponseEntity<List<ServiceOrderBbeMpx>> findServiceOrderBbeMpxById(@PathVariable Integer serviceOrderId) {
		
		Optional<List<ServiceOrderBbeMpx>> optServiceOrderBuyback = serviceOrderBbeMpxRepository.findOdsBbeMpxByServiceOrderId(serviceOrderId);
		
		if(!optServiceOrderBuyback.isPresent()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(optServiceOrderBuyback.get());
	}
	
	@RequestMapping(value = "/odsBuyback/{serviceOrderId}", method = RequestMethod.GET)
	public ResponseEntity<ServiceOrderBuyback> findServiceOrderBuybackById(@PathVariable Integer serviceOrderId) {
		
		Optional<ServiceOrderBuyback> optServiceOrderBuyback = serviceOrderBuybackRepository.findOdsBuybackByServiceOrderId(serviceOrderId);
		
		if(!optServiceOrderBuyback.isPresent()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(optServiceOrderBuyback.get());
	}
	
	
	/** ============== Teste de Funcionalidades */
	
	@RequestMapping(value = "/verifySla/{serialNumber}", method = RequestMethod.GET)
	public ResponseEntity<ReceiveView> verifySla(@PathVariable String serialNumber) {
		
		ReceiveView view = srviceOrderService.validateSlaBySerialNumber(serialNumber, true);
		
		if(view == null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(view);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody RequestReceived save(@RequestBody RequestReceived requestReceived) {
		return requestReceived;
	}
	
	@RequestMapping(value = "/confirmQuote/{serialNumber}", method = RequestMethod.GET)
	public ResponseEntity<PricingMatrixQuoteOutput> confirmQuote(@PathVariable String serialNumber) {
		
		
		PricingMatrixQuoteOutput response;
		try {
			response = null;//facade.confirmQuoteDevice();
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
		
		if(response == null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(response);
		
	}
	
	
	@RequestMapping(value = "/axDimension/{serialNumber}", method = RequestMethod.GET)
	public String findDimension(@PathVariable String serialNumber) {
		interfaceBuybackService.findDimension(serialNumber);
		return "OK";
	}
	
	
	@RequestMapping(value = "/checklistGroup/{id}", method = RequestMethod.GET)
	public ResponseEntity<ChecklistGroup> findChecklistGroup(@PathVariable Integer id) {
		
		Optional<ChecklistGroup> optChecklistGroup = checklistGroupRepository.findById(id);
		
		if(!optChecklistGroup.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(optChecklistGroup.get());
	}
	
	@RequestMapping(value = "/interfaceRequest/{transaction}", method = RequestMethod.GET)
	public ResponseEntity<InterfaceRequest> findInterfaceRequest(@PathVariable Integer transaction) {
		
		Optional<InterfaceRequest> optInterfaceRequest = interfaceRequestRepository.findById(transaction);
		
		if(!optInterfaceRequest.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(optInterfaceRequest.get());
	}
	
	@RequestMapping(value = "/modelPartNumber/{modelId}", method = RequestMethod.GET)
	public ResponseEntity<List<ModelPartNumber>> findMpn(@PathVariable Integer modelId) {
		
		List<ModelPartNumber> listMpn = modelPartNumberRepository.findByModelId(modelId);
		
		if(listMpn.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(listMpn);
		
	}
	
	
	@RequestMapping(value = "/interfaceXref/{xref}", method = RequestMethod.GET)
	public ResponseEntity<InterfaceXref> findInterfaceXref(@PathVariable String xref) {
		
		InterfaceXref interfaceXref = interfaceXrefRepository.findByCountryIdAndXrefAndEntity(292, xref, "MODEL");
		
		if(interfaceXref == null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(interfaceXref);
		
	}
	
	@RequestMapping(value = "/pendentTransfer", method = RequestMethod.GET)
	public ResponseEntity<List<TransactionUnit>> findPendentTransfer(){
		
		List<TransactionUnit> list = utilsService.findPendentesTransaferencias();
		
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(list);
		
	}
	
	@RequestMapping(value = "/interface/{serviceOrderId}", method = RequestMethod.GET)
	public ResponseEntity<List<InterfacePoOrderDetails>> findInterface(@PathVariable Integer serviceOrderId){
		
		List<InterfacePoOrderDetails> list = interfacePoOrderRep.findByServiceOrderId(serviceOrderId);
		
		List<InterfacePoOrderDetails> list2 = interfacePoOrderRep.findByServiceOrderIdAndBbLineRefNbrBbRefNumberPoNumberIsNotNull(serviceOrderId);
		
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(list2 != null ? list2 : list);
		
	}
	
	
	
	@RequestMapping(value = "/transferDeposit/{serviceOrderId}", method = RequestMethod.GET)
	public ResponseEntity<ResultTransferAx> transferDeposit(@PathVariable Integer serviceOrderId) {
		
		BuybackDepositType type = BuybackDepositType.QUARANTINE_DEPOSIT;
		
		ResultTransferAx result = null;
		try {
			result = customerServiceAx.buybackIntregrationAx(serviceOrderId, null, type, "JOB_TRANSFER_AX", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result == null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/carrier/{countryId}", method = RequestMethod.GET)
	public List<Carrier> findCarrier(@PathVariable Integer countryId) {
		return utilsService.findListCarrierByCountry(countryId,"1");
	}
	
	/*
	 * @RequestMapping(value="/serviceOrderFile", method = RequestMethod.GET) public
	 * ResponseEntity<List<ServiceOrderFile>> findServiceOrderFile() {
	 * 
	 * List<ServiceOrderFile> list = utilsService.findDiscrepancyRownum();
	 * 
	 * if(list.size() > 0) { return ResponseEntity.noContent().build(); } return
	 * ResponseEntity.ok(list); }
	 */
	
	
	/*
	FROM_TO_CHECKLIST
	INTERFACE_PO_CREATION_DETAILS
	INTERFACE_PO_CREATION_HEADER
	INTERFACE_PO_ORDER_DETAILS
	OPERATOR
	TRANSFER_DEPOSIT_AX
	*/
	
}
