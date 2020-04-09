package com.brightstar.plibmobi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightstar.plibmobi.config.Translator;
import com.brightstar.plibmobi.exceptionhandler.BusinessException;
import com.brightstar.plibmobi.exceptionhandler.PurchaseValidateException;
import com.brightstar.plibmobi.model.Checklist;
import com.brightstar.plibmobi.model.InterfaceConfig;
import com.brightstar.plibmobi.model.dto.PurchaseGradingDto;
import com.brightstar.plibmobi.model.dto.ServiceOrderDto;
import com.brightstar.plibmobi.model.dto.SourcesDto;
import com.brightstar.plibmobi.model.view.ResultAjax;
import com.brightstar.plibmobi.service.ChecklistService;
import com.brightstar.plibmobi.service.InterfaceService;
import com.brightstar.plibmobi.service.ServiceOrderService;
import com.brightstar.plibmobi.service.UtilsService;
import com.brightstar.plibmobi.util.ProcessStep;
import com.brightstar.plibmobi.ws.services.CustomerServiceAx;

@Controller
@RequestMapping("/purchase")
public class PurchaseController extends BaseController {
	
	private static final Logger LOGGER = LogManager.getLogger(PurchaseController.class);
 
	
	@Autowired 
	private UtilsService utilsService;
	 
	@Autowired
	private ServiceOrderService serviceOrderService;
	
	@Autowired
	private CustomerServiceAx customerServiceAx;
	
	@Autowired
	private ChecklistService checklistService;
	
	@Autowired
	private InterfaceService interfaceService;
	
	
	
	
	@GetMapping("/init")
	public ModelAndView init() {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("titlePage", Translator.getText("label.purchase.title"));
		mv.setViewName("grading/purchase");
		
		LOGGER.info("PurchaseController: init ", mv);
		
		return mv;
	}
	
	
	/**
	 * Efetua a validação no BB3
	 * 
	 * @param types
	 * @return
	 */
	@RequestMapping(value = "/validateBB3", method = RequestMethod.GET)
	public @ResponseBody PurchaseGradingDto validateBB3(
			@RequestParam("types") String types, 
			@RequestParam("order") Integer order, 
			@RequestParam("modelCorrect") Integer modelCorrect) {
		
		
		String[] checkTypesId = types.split("-");
		List<Integer> evaluations = new ArrayList<Integer>();
		
		for(String id : checkTypesId) {
			evaluations.add(Integer.valueOf(id));
		}
		
		
		PurchaseGradingDto purchaseGradingDto = null;
		try {
			purchaseGradingDto = interfaceService.validateEvaluation(evaluations, order, modelCorrect);
		} catch(PurchaseValidateException e) {
			
			String message = "Validação BB3 - ["+e.getErrCode()+"] " + e.getErrMsg();
			
			LOGGER.error(message, e);
			purchaseGradingDto = new PurchaseGradingDto();
			purchaseGradingDto.setStatus(false);
			purchaseGradingDto.setMessage(message);
			purchaseGradingDto.setQuoteOutput(e.getQuoteOutput());
		}
		return purchaseGradingDto;
	}
	
	
	
	/**
	 * Efetua a busca do questionário do Grade de Compra
	 * 
	 * @param countryId
	 * @return
	 */
	@RequestMapping(value = "/questions", method = RequestMethod.GET)
	public @ResponseBody List<Checklist> findQuestionsByCountryId(@RequestParam("countryId") Integer countryId) {
		return checklistService.findQuestionsByCountryId(countryId);
	}
	
	
	
	/**
	 * Valida o serial 
	 * 
	 * @param serialNumber
	 * @param request
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/searchImei", method = RequestMethod.GET)
	public @ResponseBody ResultAjax findServicOrderByImeiWithSteps(@RequestParam("serialNumber") String serialNumber, HttpServletRequest request, HttpSession httpSession ) {
		
		ServiceOrderDto serviceOrderDto = serviceOrderService.findServicOrderByImeiNotClosedAndCancel(serialNumber);
		ResultAjax result = null;
		
		if(serviceOrderDto != null) {
			
			
			/* Validação do Step da ODS*/
			if(!ProcessStep.AWAITING_PURCHASE_GRADE.name().equalsIgnoreCase(serviceOrderDto.getProcessStep())) {
				LOGGER.info("PurchaseController: findServicOrderByImeiWithSteps ", new
						ResultAjax(Translator.getText("message.purchase.error.step"), 0, null));
				return new ResultAjax(Translator.getText("message.purchase.error.step"), 0, null);
			}

			
			// Consulta AX para verificar a dimensão
			try {
				SourcesDto sources = customerServiceAx.findDimension(serialNumber);
				
				StringBuffer sb = new StringBuffer();
				
				if(sources != null && sources.getMsgError().size() > 0) {
					
					for(String error : sources.getMsgError()) {
						sb.append(error);
						if(sources.getMsgError().indexOf(error) != sources.getMsgError().lastIndexOf(error)) {
							sb.append("<br>");
						}
					}
					LOGGER.info("PurchaseController: findServicOrderByImeiWithSteps ", new ResultAjax(sb.toString(), 1, null));
					return new ResultAjax(sb.toString(), 1, null);
				}
				
				
				// Valida deposito/localidade do estoque no AX
				if(StringUtils.isEmpty(httpSession.getAttribute("DEPOSIT_PURCHASEGRADE")) ||
						StringUtils.isEmpty(httpSession.getAttribute("LOCATION_PURCHASEGRADE"))) {
					
					InterfaceConfig config = utilsService.findInterfaceConfigByGroupAndName("DIMENSION_DEPOSIT_LOCATION", "PURCHASE_GRADING");
		            String secondPass = config.getValue();
		            
		            if(!StringUtils.isEmpty(secondPass) && secondPass.split("_").length == 2) {
		            	httpSession.setAttribute("DEPOSIT_PURCHASEGRADE", secondPass.split("_")[0]); 
		            	httpSession.setAttribute("LOCATION_PURCHASEGRADE", secondPass.split("_")[1]);
		            }
				}
				if(!request.getSession().getAttribute("DEPOSIT_PURCHASEGRADE").toString().equalsIgnoreCase(sources.getDeposit()) ||
						!request.getSession().getAttribute("LOCATION_PURCHASEGRADE").toString().equalsIgnoreCase(sources.getLocation())) {
					return new ResultAjax(Translator.getText("message.errorAx.dimension", new String[] {serialNumber}), 1, null);
				}
				
				
			} catch (Exception e) {
				LOGGER.error("PurchaseController: findServicOrderByImeiWithSteps ", new
						ResultAjax(Translator.getText("message.errorAx.exception", new String[] {e.getMessage()}), 1, null));
				return new ResultAjax("Erro Inexperado", Translator.getText("message.errorAx.exception", new String[] {e.getMessage()}), 1, null);
	        }
			
				
				
			/*
			 * List<Model> models =
			 * utilsService.findModelByCountryId(serviceOrderDto.getCountryId());
			 * result.setList(models);
			 */
		
			result = new ResultAjax("", 0, serviceOrderDto);

			return result;
				
			
		} else {
			LOGGER.info("PurchaseController: findServicOrderByImeiWithSteps ", new
					ResultAjax(Translator.getText("message.purchase.error.serial", new String[]{serialNumber}), 0, null));
			return new ResultAjax("ERRO", Translator.getText("message.purchase.error.serial", new String[]{serialNumber}), 0, null);
		}
		
	}
	
	
	@ExceptionHandler({Exception.class})
	public ModelAndView handleEmployeeNotFoundException(HttpServletRequest request, BusinessException ex, org.springframework.ui.Model attr){
		LOGGER.error("Requested URL="+request.getRequestURL());
		LOGGER.error("Exception Raised="+ex);
	    
	    attr.addAttribute("exception", ex.getErrMsg());
	    return init();
	}	
	
}
