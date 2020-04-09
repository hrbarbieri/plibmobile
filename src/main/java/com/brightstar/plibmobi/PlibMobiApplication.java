package com.brightstar.plibmobi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
// @EnableScheduling - ainda sem necessidade de uso
public class PlibMobiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PlibMobiApplication.class, args);
	}

}
