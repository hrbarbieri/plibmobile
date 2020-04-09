package com.brightstar.plibmobi.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

//@Component
public class InitConfiguration implements CommandLineRunner, ApplicationRunner, ApplicationListener<ApplicationEvent> {

	private static final Logger LOGGER = LogManager.getLogger(InitConfiguration.class);
	
	
	/**
	 * After Startup
	 */
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		LOGGER.info("event: onApplicationEvent");
	}
	
	
	/**
	 * 01 - Before Startup
	 * 
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOGGER.info("run:ApplicationRunner");
	}
	
	
	/**
	 * 02 - Before Startup
	 * 
	 */
	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("run:CommandLineRunner");
	}


}
