package com.brightstar.plibmobi.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public enum ProcessStep {

	CREATED("processStep.created"),
	SHIPPED_TO_BRIGHTSTAR("processStep.shippedToBrightstar"),
	
	BRIGHTSTAR_RECEIVED_FROM_CAE("processStep.received"),
	WAITING_TRANSFER_QUARANTINE_AX("processStep.awaitingTransferAx"),
	
	AWAITING_PURCHASE_GRADE("processStep.awaitingPurchaseGrade"),
    AWAITING_SALES_GRADE("processStep.awaitingSalesGrade"),
	
	TRANSFERRED_TO_QUARANTINE_AX("processStep.transferedAx"),
	CLOSED("processStep.closed"),
	CANCELED("processStep.canceled");
	
	
	private String label;
	
	ProcessStep(String label) {
		this.label = label;
	}
	
	public static Collection<ProcessStep> convert(List<String> steps) {
		
		List<ProcessStep> colSteps = new ArrayList<ProcessStep>();
		
		for(String step : steps) {
			
			ProcessStep processStep = ProcessStep.valueOf(step);
			
			if(processStep != null) {
				colSteps.add(processStep);
			}
			
		}
		
		return colSteps;
		
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
