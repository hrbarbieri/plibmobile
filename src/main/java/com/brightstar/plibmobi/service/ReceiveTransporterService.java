package com.brightstar.plibmobi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightstar.plibmobi.model.ReceivedTransporter;
import com.brightstar.plibmobi.model.ReceivedTransporter.ReceivedTransporterType;
import com.brightstar.plibmobi.model.dto.ReceivedTransporterDto;
import com.brightstar.plibmobi.model.dto.ReceivedTransporterParam;
import com.brightstar.plibmobi.repository.ReceivedTransporterRepository;

@Service
public class ReceiveTransporterService {
	
	@Autowired
	private ReceivedTransporterRepository receivedTransporterRepository;
	
	
	public void save(ReceivedTransporterDto parans) {
		
		
		for(ReceivedTransporterParam param : parans.getParans()) {
			
			ReceivedTransporter bean = new ReceivedTransporter();
			
			bean.setServiceOrderId(param.getServiceOrderId());
			bean.setProviderId(parans.getProviderId());
			bean.setReceiveType(parans.getType());
			
			if(parans.getType().name().equals(ReceivedTransporterType.MALFUNCTIONS.name())) {
                
                if(param.getCollectedBreakdown() != null 
                        && param.getCollectedBreakdown().equals("0") 
                        && parans.getTierDefault() != null 
                        && param.getPartNumber().equals(parans.getTierDefault())) {
                	
                    bean.setDiscrepancyTransporter("1");
                }
                
            } else {
                bean.setDiscrepancyTransporter("0");
            }
            
            bean.setCollectedMalfunctions(param.getCollectedBreakdown());
            bean.setTierBbe(new Integer(param.getPartNumber()));
            bean.setCreateUserId(parans.getUser());
            bean.setCreateTimestamp(parans.getCreateDate());
            
            receivedTransporterRepository.save(bean);
            
		}
		
		
	}

}
