package com.brightstar.plibmobi.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ServiceOrderDto implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private BigDecimal customerNetPrice;
	
	private String sumPrice;
	private Integer countryId;
	private String serialNumber;
	private Integer outPortfolio;
	private Integer modelId;
	private String modelName;
	private Integer mpxId;
	private Integer handsetQuoteId;
	private Integer collectedBroken;
	private String accessKeyNF;
	private String grandingInit;
	private String processStep;
	private String countryName;
	private Integer operatorXref;
	private String voucher;

	public Integer getMpxId() {
		return mpxId;
	}


	public void setMpxId(Integer mpxId) {
		this.mpxId = mpxId;
	}


	public Integer getHandsetQuoteId() {
		return handsetQuoteId;
	}


	public void setHandsetQuoteId(Integer handsetQuoteId) {
		this.handsetQuoteId = handsetQuoteId;
	}


	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}


	public ServiceOrderDto() {
	}


	public ServiceOrderDto(Integer id) {
        this.id = id;
    }
	
	public Integer getCountryId() {
		return countryId;
	}


	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}


	public String getProcessStep() {
		return processStep;
	}


	public void setProcessStep(String processStep) {
		this.processStep = processStep;
	}


	public String getGrandingInit() {
		return grandingInit;
	}

	public void setGrandingInit(String grandingInit) {
		this.grandingInit = grandingInit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public BigDecimal getCustomerNetPrice() {
		return customerNetPrice;
	}
	public void setCustomerNetPrice(BigDecimal customerNetPrice) {
		this.customerNetPrice = customerNetPrice;
	}
	public String getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(String sumPrice) {
		this.sumPrice = sumPrice;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Integer getOutPortfolio() {
		return outPortfolio;
	}
	public void setOutPortfolio(Integer outPortfolio) {
		this.outPortfolio = outPortfolio;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public Integer getCollectedBroken() {
		return collectedBroken;
	}
	public void setCollectedBroken(Integer collectedBroken) {
		this.collectedBroken = collectedBroken;
	}
	public String getAccessKeyNF() {
		return accessKeyNF;
	}
	public void setAccessKeyNF(String accessKeyNF) {
		this.accessKeyNF = accessKeyNF;
	}
	public Integer getOperatorXref() {
		return operatorXref;
	}
	public void setOperatorXref(Integer operatorXref) {
		this.operatorXref = operatorXref;
	}
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	public String getVoucher() {
		return voucher;
	}
	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

}
