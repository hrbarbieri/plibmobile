package com.brightstar.plibmobi.model.dto;

import java.io.Serializable;

public class ReceivedTransporterParam implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;
	
	private Integer serviceOrderId;
	private String partNumber;
	private String collectedBreakdown;
	
	public ReceivedTransporterParam(Integer serviceOrderId, String partNumber, String collectedBreakdown) {
		this.serviceOrderId = serviceOrderId;
		this.partNumber = partNumber;
		this.collectedBreakdown = collectedBreakdown;
	}
	
	public String getCollectedBreakdown() {
		return collectedBreakdown;
	}
	public void setCollectedBreakdown(String collectedBreakdown) {
		this.collectedBreakdown = collectedBreakdown;
	}
	public Integer getServiceOrderId() {
		return serviceOrderId;
	}
	public void setServiceOrderId(Integer serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	
}
