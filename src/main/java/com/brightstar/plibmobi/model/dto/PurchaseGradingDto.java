package com.brightstar.plibmobi.model.dto;

import java.io.Serializable;
import java.util.List;

import com.brightstar.mpx.client.PricingMatrixErrorReason;
import com.brightstar.mpx.client.PricingMatrixQuoteOutput;
import com.brightstar.plibmobi.model.FromToChecklistType;

public class PurchaseGradingDto implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;
	
	private List<FromToChecklistType> fromToLst;
	
	private PricingMatrixQuoteOutput quoteOutput;	

	private Boolean status;
	
	private Boolean discrepancy;
	
	private String message;
	
	private String partNumberResult;
	
	private PricingMatrixErrorReason[] errorReason;
	
	
	public PurchaseGradingDto() {
	}
	
	public PurchaseGradingDto(String message, List<FromToChecklistType> fromToLst, PricingMatrixQuoteOutput quoteOutput) {
		this.message = message;
		this.fromToLst = fromToLst;
		this.quoteOutput = quoteOutput;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	public List<FromToChecklistType> getFromToLst() {
		return fromToLst;
	}

	public void setFromToLst(List<FromToChecklistType> fromToLst) {
		this.fromToLst = fromToLst;
	}

	public PricingMatrixQuoteOutput getQuoteOutput() {
		return quoteOutput;
	}

	public void setQuoteOutput(PricingMatrixQuoteOutput quoteOutput) {
		this.quoteOutput = quoteOutput;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPartNumberResult() {
		return partNumberResult;
	}

	public void setPartNumberResult(String partNumberResult) {
		this.partNumberResult = partNumberResult;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getDiscrepancy() {
		return discrepancy;
	}

	public void setDiscrepancy(Boolean discrepancy) {
		this.discrepancy = discrepancy;
	}

	public PricingMatrixErrorReason[] getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(PricingMatrixErrorReason[] errorReason) {
		this.errorReason = errorReason;
	}
	
}
