package com.brightstar.plibmobi.listeners;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class Handler implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if(request.getServletContext().getAttribute("SESSION_APPLICATION") != null 
				&& !request.getServletContext().getAttribute("SESSION_APPLICATION").equals(request.getSession().getId())) {
			modelAndView.setStatus(HttpStatus.REQUEST_TIMEOUT);
		}
	}
	

}
