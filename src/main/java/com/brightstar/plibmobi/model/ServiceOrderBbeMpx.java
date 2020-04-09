package com.brightstar.plibmobi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SERVICE_ORDER_BBE_MPX", schema = "RMAAPROWN")
@JsonIgnoreProperties({"serviceOrder", "hibernateLazyInitializer", "handler"})
public class ServiceOrderBbeMpx implements Serializable {
	
	/** */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="ORDER_BBE_MPX_SEQ", schema="RMAAPROWN")
	private Long id;	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "SERVICE_ORDER_ID")
	private ServiceOrder serviceOrder;
	
	@Column(name="ITG_TYPE")
	private Integer itgType;
	
	@Column(name="EG_PART_NUMBER")
	private String partNumber;
	
	@Column(name="EG_GRADING_FINAL")
	private String grandingInit;
	
	@Column(name="STORE_ID")
	private String storeId;
	
	@Column(name="STORE_NAME")
	private String storeName;
	
	@Column(name="AGENT_ID")
	private String agendId;
	
	private String region;
	
	@Column(name="CURRENCY_CODE")
	private String currencyCode;
	
	@Column(name="CUSTOMER_NET_PRICE")
	private BigDecimal customerNetPrice;
	
	@Column(name="NET_PRICE")
	private BigDecimal netPrice;
	
	@Column(name="GROSS_PRICE")
	private BigDecimal grossPrice;

	@Column(name="DEDUCTION")
	private BigDecimal deduction;
	
	@Column(name="QUOTE_DATE")
	private Date quoteDate;
	
	@Column(name="HANDSET_QUOTE_ID")
	private Integer handsetQuoteId;
	
	@Column(name="RESPONSE_STATUS")
	private String responseStatus;
	
	@Column(name="NETWORK_ID")
	private Integer networkId;
	
	@Column(name="REGION_ID")
	private Integer regionId;
	
	private String voucher;
	
	@Column(name="MPX_MODEL_ID")
	private Integer mpxModelId;
	
	private String model;
	
	@Column(name="GRADING")
	private Integer gradingFinal;
	
	@Column(name="OUT_PORTFOLIO")
	private Integer outportfolio;
	
	private String division;
	
	@Column(name="SUB_DIVISION")
	private String subDivision;
    
	@Column(name="CREATE_DATE")
	private Date createTimestamp;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ServiceOrder getServiceOrder() {
		return serviceOrder;
	}

	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

	public Integer getItgType() {
		return itgType;
	}

	public void setItgType(Integer itgType) {
		this.itgType = itgType;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getGrandingInit() {
		return grandingInit;
	}

	public void setGrandingInit(String grandingInit) {
		this.grandingInit = grandingInit;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAgendId() {
		return agendId;
	}

	public void setAgendId(String agendId) {
		this.agendId = agendId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public BigDecimal getCustomerNetPrice() {
		return customerNetPrice;
	}

	public void setCustomerNetPrice(BigDecimal customerNetPrice) {
		this.customerNetPrice = customerNetPrice;
	}

	public BigDecimal getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(BigDecimal netPrice) {
		this.netPrice = netPrice;
	}

	public BigDecimal getGrossPrice() {
		return grossPrice;
	}

	public void setGrossPrice(BigDecimal grossPrice) {
		this.grossPrice = grossPrice;
	}

	public BigDecimal getDeduction() {
		return deduction;
	}

	public void setDeduction(BigDecimal deduction) {
		this.deduction = deduction;
	}

	public Date getQuoteDate() {
		return quoteDate;
	}

	public void setQuoteDate(Date quoteDate) {
		this.quoteDate = quoteDate;
	}

	public Integer getHandsetQuoteId() {
		return handsetQuoteId;
	}

	public void setHandsetQuoteId(Integer handsetQuoteId) {
		this.handsetQuoteId = handsetQuoteId;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public Integer getNetworkId() {
		return networkId;
	}

	public void setNetworkId(Integer networkId) {
		this.networkId = networkId;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public Integer getMpxModelId() {
		return mpxModelId;
	}

	public void setMpxModelId(Integer mpxModelId) {
		this.mpxModelId = mpxModelId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getGradingFinal() {
		return gradingFinal;
	}

	public void setGradingFinal(Integer gradingFinal) {
		this.gradingFinal = gradingFinal;
	}

	public Integer getOutportfolio() {
		return outportfolio;
	}

	public void setOutportfolio(Integer outportfolio) {
		this.outportfolio = outportfolio;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getSubDivision() {
		return subDivision;
	}

	public void setSubDivision(String subDivision) {
		this.subDivision = subDivision;
	}

	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	

}
