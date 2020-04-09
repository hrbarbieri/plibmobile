package com.brightstar.plibmobi.exceptionhandler;

import com.brightstar.mpx.client.PricingMatrixQuoteOutput;

public class PurchaseValidateException extends RuntimeException {

	/** */
	private static final long serialVersionUID = 1L;
	
	private PricingMatrixQuoteOutput quoteOutput;
	private String errCode;
	private String errMsg;
	
	public PurchaseValidateException() {
	}
	
	public PurchaseValidateException(String errCode, String errMsg, PricingMatrixQuoteOutput quoteOutput) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.quoteOutput = quoteOutput;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public PricingMatrixQuoteOutput getQuoteOutput() {
		return quoteOutput;
	}

	public void setQuoteOutput(PricingMatrixQuoteOutput quoteOutput) {
		this.quoteOutput = quoteOutput;
	}
	
}
