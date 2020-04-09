package com.brightstar.plibmobi.ws;

import org.springframework.stereotype.Component;

import com.brightstarcorp.erp.core.to.entity.inventSerialLastDimService.webservice.ax.erp.brightstarcorp.com.InventSerialLastDimServiceFindAifFaultFaultFaultMessage;
import com.brightstarcorp.erp.facade.InventSerialLastDimServiceFacade;
import com.microsoft.schemas.dynamics._2006._02.documents.entitykey.EntityKey;
import com.microsoft.schemas.dynamics._2006._02.documents.entitykey.KeyField;
import com.microsoft.schemas.dynamics._2006._02.documents.entitykeylist.EntityKeyList;

@Component
public class InterfaceBuybackService {

	private InventSerialLastDimServiceFacade inventSerialLastDimClient;
	
	public void findDimension(String serialNumber) {
		
		 String company = "BR06";
		 String endpoint = "http://dcla-p-esb-01.dcla.local:7629/brightstar-esb/services/brazil/ax";
		 String endpointAddress = "/inventseriallastdimservice";
		 
		 EntityKeyList entityKeyList = null;
         InventSerialLastDimServiceFacade client = this.getWSClientInventSerialLastDim(endpoint+endpointAddress);
         
         try {
			entityKeyList = client.find(company, serialNumber);
			
			 if(entityKeyList!= null && entityKeyList.getEntityKey().size() > 0) {
	                
	                for(EntityKey entityKey : entityKeyList.getEntityKey()) {
	                    for(KeyField keyField : entityKey.getKeyData().getKeyField()) {
	                        
	                        if("wMSLocationId".equalsIgnoreCase(keyField.getField())) { // Localização
	                            System.out.println(keyField.getValue());
	                        } else if("InventLocationId".equalsIgnoreCase(keyField.getField())) { // Depósito
	                        	System.out.println(keyField.getValue());
	                        } else if("ItemName".equalsIgnoreCase(keyField.getField())) { // Nome do Modelo
	                        	System.out.println(keyField.getValue());
	                        } else if("ItemId".equalsIgnoreCase(keyField.getField())) { // Part Number
	                        	System.out.println(keyField.getValue());
	                        }
	                    }
	                }
			 }
			
		} catch (InventSerialLastDimServiceFindAifFaultFaultFaultMessage e) {
			e.printStackTrace();
		}
         
         
	}
	
	
	private InventSerialLastDimServiceFacade getWSClientInventSerialLastDim(String endpointAddress) {
        
        if(inventSerialLastDimClient != null) {
            return inventSerialLastDimClient;
        }
        
        inventSerialLastDimClient = new InventSerialLastDimServiceFacade();
        inventSerialLastDimClient.setEndpointAddress(endpointAddress);
        return inventSerialLastDimClient;
        
    }
	
	
}
