package com.brightstar.plibmobi.exceptionhandler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.brightstar.plibmobi.util.DateUtil;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleSQLException(HttpServletRequest request, Exception ex){
		logger.info("SQLException Occured:: URL="+request.getRequestURL());
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("exceptionDate", DateUtil.formatDate(new Date(), DateUtil.DATE_TIME_SEC_PATTERN));
		mv.addObject("exception1", ex.toString());
		mv.setViewName("error");
		
		return mv;
	}
	
	@ExceptionHandler(TimeoutException.class)
	public ModelAndView timeoutException(HttpServletRequest request, Exception ex){
		logger.info("SQLException Occured:: URL="+request.getRequestURL());
		ModelAndView mv = new ModelAndView();
		mv.setStatus(HttpStatus.REQUEST_TIMEOUT);
		return mv;
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
	@ExceptionHandler(IOException.class)
	public void handleIOException(){
		//returning 404 error code
	}


}
