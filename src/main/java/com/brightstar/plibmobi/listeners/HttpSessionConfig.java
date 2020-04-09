package com.brightstar.plibmobi.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpSessionConfig {
	
	@Bean // bean for http session listener
	public HttpSessionListener httpSessionListener() {
		return new HttpSessionListener() {
			@Override
			public void sessionCreated(HttpSessionEvent se) {
				System.out.println("Session Created with session id: " + se.getSession().getId());
			}

			@Override
			public void sessionDestroyed(HttpSessionEvent se) { // This method will be automatically called when session
				System.out.println("sessionDestroyed: " + se.getSession().getId());
			}
		};
	}

}
