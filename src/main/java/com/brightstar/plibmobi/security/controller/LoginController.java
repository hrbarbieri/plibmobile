package com.brightstar.plibmobi.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.brightstar.plibmobi.config.Translator;
import com.brightstar.plibmobi.controller.BaseController;
import com.brightstar.plibmobi.model.InterfaceConfig;
import com.brightstar.plibmobi.service.UtilsService;

@Controller
public class LoginController extends BaseController {
	
	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
	
	@Autowired
	private UtilsService utilsService;
	
	@GetMapping("/login")
	public String login(HttpServletRequest request) {
		LOGGER.info("LoginController: login");
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		LOGGER.info("LoginController: logout");
		return "redirect:/login?logout";
	}
	
	@GetMapping("/")
	public ModelAndView home(HttpServletRequest request, HttpSession httpSession) {
		
		/* Guarda o ID corrente da session para controle de timeout */
		request.getServletContext().setAttribute("SESSION_APPLICATION", request.getRequestedSessionId());

		
		/* Configurações iniciais para a Grade de Compras */
		if(StringUtils.isEmpty(httpSession.getAttribute("DEPOSIT_PURCHASEGRADE")) ||
				StringUtils.isEmpty(httpSession.getAttribute("LOCATION_PURCHASEGRADE"))) {
			
			InterfaceConfig config = utilsService.findInterfaceConfigByGroupAndName("DIMENSION_DEPOSIT_LOCATION", "PURCHASE_GRADING");
            String secondPass = config.getValue();
            
            if(!StringUtils.isEmpty(secondPass) && secondPass.split("_").length == 2) {
            	httpSession.setAttribute("DEPOSIT_PURCHASEGRADE", secondPass.split("_")[0]); 
            	httpSession.setAttribute("LOCATION_PURCHASEGRADE", secondPass.split("_")[1]);
            }
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("titlePage", Translator.getText("label.home.title"));
		mv.setViewName("home");
		
		
		// Carrega Grade Compra automaticamente
		for(GrantedAuthority grantedAuthority : getAuth().getAuthorities()) {
			if(grantedAuthority.getAuthority().equals("JRMAT_IN_TRANSIT_RECEIVE")) {
				mv.setViewName("redirect:receive/init");
				mv.addObject("titlePage", Translator.getText("label.received.title"));
			}
		}
		
		
		httpSession.setAttribute("uri", request.getRequestURI());
		 
		LOGGER.info("LoginController: home ", mv);
		
		return mv;
	}
	
	@GetMapping("/timeout")
	public String timeout() {
		return "redirect:/login?logout";
	}
	
}
