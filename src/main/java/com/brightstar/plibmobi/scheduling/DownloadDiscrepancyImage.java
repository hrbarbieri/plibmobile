package com.brightstar.plibmobi.scheduling;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.brightstar.plibmobi.model.InterfaceConfig;
import com.brightstar.plibmobi.service.UtilsService;

@Component
public class DownloadDiscrepancyImage {

	@Autowired
	private UtilsService utilsService;
	
	@Scheduled(cron = "${cron.expression.test}")
    public void run() {
			
		InterfaceConfig ic = utilsService.findInterfaceConfigByGroupAndName("BUYBACK_SCHEDULING", "DOWNLOAD_DISCREPANCY");
		if(ic != null && ic.getValue().equals("1")) {		
			
			
			
			
			
		}
			
    }
	
}
