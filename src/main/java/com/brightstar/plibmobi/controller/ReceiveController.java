package com.brightstar.plibmobi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightstar.plibmobi.config.Translator;
import com.brightstar.plibmobi.exceptionhandler.BusinessException;
import com.brightstar.plibmobi.model.dto.ProviderDto;
import com.brightstar.plibmobi.model.view.ReceiveForm;
import com.brightstar.plibmobi.model.view.ReceiveView;
import com.brightstar.plibmobi.service.ServiceOrderService;
import com.brightstar.plibmobi.service.UtilsService;

@Controller
@RequestMapping("/receive")
public class ReceiveController extends BaseController {
	
	private static final Logger LOGGER = LogManager.getLogger(ReceiveController.class);
	
	@Autowired
	private UtilsService utilsService;
	
	@Autowired
	private ServiceOrderService serviceOrderService;
	
	@GetMapping("/init")
	public ModelAndView init() {
		ModelAndView mv = new ModelAndView();
		
		List<ProviderDto> providers = utilsService.findProviderByType("RECEIVE_BRIGHTSTAR");
		
		mv.addObject("providers", providers);
		mv.addObject("receiveForm", new ReceiveForm());
		mv.addObject("titlePage", Translator.getText("label.received.title"));
		mv.setViewName("receive/init");
		
		LOGGER.info("ReceiveController: init ", mv);
		
		return mv;
	}
	
	
	@GetMapping("/list")
	public ModelAndView listReceived() {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("titlePage", "Consultar Recebimento");
		mv.setViewName("receive/list");
		
		LOGGER.info("ReceiveController: list ", mv);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/validateImei", method = RequestMethod.GET)
	public @ResponseBody ReceiveView verifySla(@RequestParam("serialNumber") String serialNumber, 
			@RequestParam("damage") boolean damage) {
		
		ReceiveView view = serviceOrderService.validateSlaBySerialNumber(serialNumber, damage);
		
		LOGGER.info("ReceiveController: verifySla " + view.toString());
		
		return view;
	}
	
	@PostMapping("/save")
	public ModelAndView save(ReceiveForm receiveForm, Model attr) throws Exception {
		
		LOGGER.info("ReceiveController: save ", receiveForm);
		
		Integer masterBoxId = serviceOrderService.saveReceived(receiveForm, getUserId(), getDateNow());
		
		if(masterBoxId > 0) {
			attr.addAttribute("messageSuccess", 
					Translator.getText("message.received.success", new String[] {String.valueOf(masterBoxId)}));
		} else {
			attr.addAttribute("messageError", 
					Translator.getText("message.received.error"));
		}
		
		return init();
	}
	
	
	@ExceptionHandler({BusinessException.class})
	public ModelAndView handleEmployeeNotFoundException(HttpServletRequest request, BusinessException ex, Model attr){
		LOGGER.error("Requested URL="+request.getRequestURL());
		LOGGER.error("Exception Raised="+ex);
	    
	    attr.addAttribute("exception", ex.getErrMsg());
	    return init();
	}	
	
}
