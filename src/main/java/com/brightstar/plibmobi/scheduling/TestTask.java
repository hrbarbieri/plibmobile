package com.brightstar.plibmobi.scheduling;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.brightstar.plibmobi.model.InterfaceConfig;
import com.brightstar.plibmobi.service.UtilsService;

/**
 * TODO Descomentar o @EnableScheduling em class:PlibMobiApplication
 * 
 * @author HB16131
 *
 */
@Component
public class TestTask {
	
	@Value("${job.enabled}")
	private boolean jobEnable;
	
	@Autowired
	private UtilsService utilsService;
	
	@Scheduled(cron = "${cron.expression.test}")
    public void run() {
		if(jobEnable) {
			
			InterfaceConfig ic = utilsService.findInterfaceConfigByGroupAndName("BUYBACK_SCHEDULING", "TEST_TAKS_ENABLE");
			if(ic != null && ic.getValue().equals("1")) {			
				System.out.println("Teste de execução CRON: " +  new SimpleDateFormat("HH:mm:ss").format(new Date()));
			}
			
		}
    }

}
