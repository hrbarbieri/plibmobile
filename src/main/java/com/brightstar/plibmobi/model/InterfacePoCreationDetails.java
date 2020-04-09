package com.brightstar.plibmobi.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "INTERFACE_PO_CREATION_DETAILS", schema = "RMAAPROWN")
public class InterfacePoCreationDetails {

	@Id
	@Column(name="BBLINEREFNBR")
	@SequenceGenerator(sequenceName="INTERFACE_BUYBACK_SEQ", schema="RMAAPROWN", allocationSize = 1, name = "IBB_SEQ")
	@GeneratedValue(generator="IBB_SEQ", strategy=GenerationType.AUTO)
	private Integer bbLineRefNbr; 
	
	@OneToOne(targetEntity=InterfacePoCreationHeader.class, fetch=FetchType.EAGER)
	@JoinColumn(nullable = false, name = "BBREFNBR")
	private InterfacePoCreationHeader bbRefNumber;
	
	@Column(name="COSTUNIT")
	private BigDecimal unitCost;
	
	@Column(name="CPNYID")
	private String companyId; 
	
	@Column(name="INVTID")
	private String inventoryId;

	@Column(name="LINEID")
	private Integer lineId;
	
	@Column(name="LINEREF")
	private String lineRef;
	
	@Column(name="LINENBR")
	private Integer lineNbr;
	
	@Column(name="SEQNBR")
	private String seqNbr;

	@Column(name="PRECIOUNIT")
	private BigDecimal unitPrice;
	
	@Column(name="QTYORD")
	private Integer amountOrdered;

	@Column(name="SITEID")
	private String siteId;

	@Column(name="UM")
	private String measureUnit; 
	
	@Column(name="TSTAMP")
	private Date timestamp;
	
	public Integer getBbLineRefNbr() {
		return bbLineRefNbr;
	}

	public void setBbLineRefNbr(Integer bbLineRefNbr) {
		this.bbLineRefNbr = bbLineRefNbr;
	}

	public InterfacePoCreationHeader getBbRefNumber() {
		return bbRefNumber;
	}

	public void setBbRefNumber(InterfacePoCreationHeader bbRefNumber) {
		this.bbRefNumber = bbRefNumber;
	}

	public BigDecimal getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public String getLineRef() {
		return lineRef;
	}

	public void setLineRef(String lineRef) {
		this.lineRef = lineRef;
	}

	public Integer getLineNbr() {
		return lineNbr;
	}

	public void setLineNbr(Integer lineNbr) {
		this.lineNbr = lineNbr;
	}

	public String getSeqNbr() {
		return seqNbr;
	}

	public void setSeqNbr(String seqNbr) {
		this.seqNbr = seqNbr;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getAmountOrdered() {
		return amountOrdered;
	}

	public void setAmountOrdered(Integer amountOrdered) {
		this.amountOrdered = amountOrdered;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
