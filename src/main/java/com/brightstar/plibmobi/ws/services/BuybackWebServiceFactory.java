package com.brightstar.plibmobi.ws.services;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.brightstar.plibmobi.util.StringUtil;
import com.brightstarcorp.erp.facade.InventSerialLastDimServiceFacade;
import com.brightstarcorp.erp.facade.InventTransferItemServiceFacade;

@Component
@ConfigurationProperties(prefix = "brightstar.webservices") 
public class BuybackWebServiceFactory {
	
	
	private String inventTransferItem;
	
	private String inventSerialLastDim;
	
	//private BuybackSerialWsClientFacade buybackSerialWebServiceClient;
    private InventTransferItemServiceFacade inventTransferItemWebServiceClient;
    private InventSerialLastDimServiceFacade inventSerialLastDimClient;
    
    /*
    private MercurioServiceTransferClientFacade mercurioServiceTransferClient;
    
    private ItemClientFacade itemClientFacade;
    
    public MercurioServiceTransferClientFacade getWSClientTransfer() {
        if (mercurioServiceTransferClient != null) {
            return mercurioServiceTransferClient;
        }
        String endpointAddress = EnviromentConf.getTransferWebServiceEndpointAddress();
        if (StringUtils.isBlank(endpointAddress)) {
            throw new RuntimeException("WebService Endpoint Address is not configured.");
        }
        mercurioServiceTransferClient = new MercurioServiceTransferClientFacade();
        mercurioServiceTransferClient.setEndpointAddress(endpointAddress);
        return mercurioServiceTransferClient;
    }
    
    public BuybackSerialWsClientFacade getWSClient() {
        if (buybackSerialWebServiceClient != null) {
            return buybackSerialWebServiceClient;
        }
        String endpointAddress = EnviromentConf.getBuybackWebServiceEndpointAddress();
        if (StringUtils.isBlank(endpointAddress)) {
            throw new RuntimeException("WebService Endpoint Address is not configured.");
        }
        buybackSerialWebServiceClient = new BuybackSerialWsClientFacade();
        buybackSerialWebServiceClient.setEndpointAddress(endpointAddress, "BuyBackSerialService");
        return buybackSerialWebServiceClient;
    }
    */
    
    public InventTransferItemServiceFacade getWSClientInventTransferItem() {
        
        if(inventTransferItemWebServiceClient != null) {
            return inventTransferItemWebServiceClient;
        }
        
        if (StringUtil.isBlank(inventTransferItem)) {
            throw new RuntimeException("WebService Endpoint Address is not configured.");
        }
        
        inventTransferItemWebServiceClient = new InventTransferItemServiceFacade();
        inventTransferItemWebServiceClient.setEndpointAddress(inventTransferItem);
        return inventTransferItemWebServiceClient;
        
    }
    
    public InventSerialLastDimServiceFacade getWSClientInventSerialLastDim() {
        
        if(inventSerialLastDimClient != null) {
            return inventSerialLastDimClient;
        }
        
        if (StringUtil.isBlank(inventSerialLastDim)) {
            throw new RuntimeException("WebService Endpoint Address is not configured.");
        }
        
        inventSerialLastDimClient = new InventSerialLastDimServiceFacade();
        inventSerialLastDimClient.setEndpointAddress(inventSerialLastDim);
        return inventSerialLastDimClient;
        
    }

	public String getInventTransferItem() {
		return inventTransferItem;
	}

	public void setInventTransferItem(String inventTransferItem) {
		this.inventTransferItem = inventTransferItem;
	}
	
    
    public String getInventSerialLastDim() {
		return inventSerialLastDim;
	}

	public void setInventSerialLastDim(String inventSerialLastDim) {
		this.inventSerialLastDim = inventSerialLastDim;
	}

	
    
    
    /*
    public ItemClientFacade getWSItemServiceFacade() {
        
    	if(itemClientFacade != null) {
            return itemClientFacade;
        }
        
        String endpointAddress = EnviromentConf.getItemServiceEndpointAddress();
        if (StringUtils.isBlank(endpointAddress)) {
            throw new RuntimeException("WebService Endpoint Address is not configured.");
        }
        
        itemClientFacade = new ItemClientFacade();
        itemClientFacade.setEndpointAddress(endpointAddress);
        return itemClientFacade;
    }
     */
    
    
}
