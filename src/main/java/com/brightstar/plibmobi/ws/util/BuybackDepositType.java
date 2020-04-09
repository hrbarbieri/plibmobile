package com.brightstar.plibmobi.ws.util;

public enum BuybackDepositType {
    
    TRANSIT("TRANSIT"),
    PURCHASE_GRADE("PURCHASE_GRADE"),
    SALES_GRADE("SALES_GRADE"),
	
    SALE_DEPOSIT("SALE_LOCATION"),
    WIP_DEPOSIT("WIP_LOCATION"),
	QUARANTINE_DEPOSIT("QUARANTINE_LOCATION");
	
	
    
    private String location;

    BuybackDepositType(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
}
