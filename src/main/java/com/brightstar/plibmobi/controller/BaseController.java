package com.brightstar.plibmobi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.brightstar.plibmobi.security.model.Users;

public class BaseController {
	
	/**
	 * Recupera o objeto com os dados do usuário autenticado
	 * @return
	 */
	public Authentication getAuth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	
	/**
	 * Retorna o nome do usuário logado
	 * @return
	 */
	public String getUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = null;
		
		if (principal instanceof Users) {
			name = ((Users)principal).getUserName();
		} else {
			name = principal.toString();
		}
		return name;
	}
	
	/**
	 * Retorna o User Logado
	 * @return
	 */
	public String getUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = null;
		
		if (principal instanceof Users) {
			name = ((Users)principal).getUserId();
		} 
		return name;
	}
	
	public Date getDateNow() {
		String gmt = "GMT-03:00";
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat dfZone = new SimpleDateFormat(format);
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		dfZone.setTimeZone(TimeZone.getTimeZone(gmt));
		Date date = new Date();
		try {
			date = fmt.parse(dfZone.format(new Date()));
		} catch (ParseException e) {
		}
		return date;
	}

}
