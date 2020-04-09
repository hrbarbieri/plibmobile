package com.brightstar.plibmobi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightstar.mpx.client.BuybackMpxException;
import com.brightstar.mpx.client.PricingMatrixErrorReason;
import com.brightstar.mpx.client.PricingMatrixQuoteOutput;
import com.brightstar.plibmobi.convert.ServiceOrderDtoConverter;
import com.brightstar.plibmobi.exceptionhandler.PurchaseValidateException;
import com.brightstar.plibmobi.model.ChecklistType;
import com.brightstar.plibmobi.model.FromToChecklistType;
import com.brightstar.plibmobi.model.Model;
import com.brightstar.plibmobi.model.ModelPartNumber;
import com.brightstar.plibmobi.model.Operator;
import com.brightstar.plibmobi.model.ServiceOrder;
import com.brightstar.plibmobi.model.ServiceOrderBbeMpx;
import com.brightstar.plibmobi.model.ServiceOrderBuyback;
import com.brightstar.plibmobi.model.dto.PurchaseGradingDto;
import com.brightstar.plibmobi.model.dto.ServiceOrderDto;
import com.brightstar.plibmobi.repository.FromToChecklistTypeRepository;
import com.brightstar.plibmobi.repository.ServiceOrderBuybackRepository;
import com.brightstar.plibmobi.ws.BackendBuybackMpxServiceFacade;

@Service
public class InterfaceService {

	@Autowired
	private FromToChecklistTypeRepository fromToChecklistTypeRepository;
	
	@Autowired
	private BackendBuybackMpxServiceFacade backendFacade;
	
	@Autowired
	private ServiceOrderService serviceOrderService;
	
	@Autowired
	private ServiceOrderBuybackRepository serviceOrderBuybackRepository;
	
	@Autowired
	private UtilsService utilsService;
	
	
	/**
	 * 
	 * 
	 * @param evaluations
	 * @param serviceOrderId
	 * @param correctModel
	 * @return
	 */
	public PurchaseGradingDto validateEvaluation(List<Integer> evaluations, Integer serviceOrderId, Integer correctModel) throws PurchaseValidateException {
		
		// Recupera ODS
		Optional<ServiceOrder> optServiceOrder = serviceOrderService.getServiceOrder(serviceOrderId);
		if(!optServiceOrder.isPresent()) {
			// throw new ....
		}
		ServiceOrder serviceOrder = optServiceOrder.get();

		
		// Valida o modelo correto
		Boolean modelCorrect = false;
		if(serviceOrder.getModel().getModelId().intValue() != correctModel.intValue()) {
			
			modelCorrect = true;
			
			Optional<Model> optModelCorrect =  utilsService.findModelById(correctModel);
			if(optModelCorrect.isPresent()) {
				serviceOrder.setModel(optModelCorrect.get());
			}
		}
		
		
		// Convert ODS em DTO
		ServiceOrderDto serviceOrderDto = new ServiceOrderDtoConverter().convert(serviceOrder);
		
		
		// Corrige a falta desse dados do Operation
		if(serviceOrder.getOrderBuyback() != null 
				&& serviceOrder.getOrderBuyback().getOperatorId() == null) {
			
			Optional<Operator> optOperator = utilsService.findOperatorCountryIdAndUnlocked(serviceOrder.getCountry().getCountryId(), 1);
			
			if(!optOperator.isPresent()) {
				ServiceOrderBuyback serviceOrderBuyback = serviceOrder.getOrderBuyback();
				
				serviceOrderBuyback.setOperatorId(optOperator.get().getId());
				serviceOrderBuybackRepository.save(serviceOrderBuyback);
				
				
				serviceOrderDto.setOperatorXref(Integer.valueOf(optOperator.get().getExternalReference()));
			}
		} else {
			Optional<Operator> optOperator = utilsService.findOperatorCountryIdAndUnlocked(serviceOrder.getCountry().getCountryId(), 1);
			if(optOperator.isPresent()) {
				serviceOrderDto.setOperatorXref(Integer.valueOf(optOperator.get().getExternalReference()));
			}
		}
		
		
		// Recupera a lista que ChecklistType do FROM_TO pelo seleção feita na tela
		List<FromToChecklistType> fromTo = fromToChecklistTypeRepository.findByChecklistTypeIdFromIdIn(evaluations);
		List<ChecklistType> backs = new ArrayList<ChecklistType>();
		
		fromTo.forEach(item -> {
			backs.add(item.getChecklistTypeIdBack());
		});
		
		
		PricingMatrixQuoteOutput quoteOutput = null;
		PurchaseGradingDto purchaseGrading = null;
		try {
			// Response do BB3
			quoteOutput = backendFacade.confirmQuoteDevice(serviceOrderDto, backs);
			
			if(quoteOutput.getErrorReasons().length > 0) {
				StringBuffer errorReasons = new StringBuffer();
				for(PricingMatrixErrorReason reason : quoteOutput.getErrorReasons()) {
					errorReasons.append(reason.getErrorReason());
				}
				throw new PurchaseValidateException("001", errorReasons.toString(), quoteOutput);
			} else {
			
				// Recupera o PN referente ao grade retornado do BB3
				ModelPartNumber modelPartNumber = utilsService.findModelPartNumber(
						serviceOrderDto.getCountryId(), serviceOrderDto.getModelId(), String.valueOf(quoteOutput.getGrading()));
				
				if(modelPartNumber == null) {
					throw new PurchaseValidateException("002","Part Number não encontrado.", quoteOutput);
				}
				
				purchaseGrading = new PurchaseGradingDto();
				purchaseGrading.setQuoteOutput(quoteOutput);
				purchaseGrading.setPartNumberResult(modelPartNumber != null ? modelPartNumber.getPartNumber() : "PN não encontrado.");
				purchaseGrading.setFromToLst(fromTo);
				
				purchaseGrading.setStatus(true); // Sem erro de validação
				purchaseGrading.setDiscrepancy(false); //Sem discrepancia
				
				
				// Validação de Valor
				if(!serviceOrder.getOrderBbeMpx().isEmpty()) {
					ServiceOrderBbeMpx bbeMpx =serviceOrder.getOrderBbeMpx().get(0);
					
					BigDecimal discrepancyValue = bbeMpx.getNetPrice();
					if(discrepancyValue.compareTo(quoteOutput.getNetPrice()) != 0) {
						purchaseGrading.setMessage("Discrepância no Grading - valor de cotaçâo divergente entre os sistemas BB3 e PLIB.");
						purchaseGrading.setDiscrepancy(true);
					}
				}
			}
		} catch (BuybackMpxException e) {
			throw new PurchaseValidateException("003", e.getMessage(), null);
		}
		
		return purchaseGrading;
	}
	
	
	
}
