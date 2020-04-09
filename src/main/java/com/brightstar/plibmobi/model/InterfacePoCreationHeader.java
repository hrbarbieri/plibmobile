package com.brightstar.plibmobi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="INTERFACE_PO_CREATION_HEADER", schema="RMAAPROWN")
public class InterfacePoCreationHeader {
	
	@Id
	@Column(name="BBREFNBR")
	@SequenceGenerator(sequenceName="INTERFACE_BUYBACK_SEQ", schema="RMAAPROWN", allocationSize = 1, name = "IBB_SEQ")
	@GeneratedValue(generator="IBB_SEQ", strategy=GenerationType.AUTO)
	private Integer bbRefNumber; 
	
	@Column(name="CPNYID")
    private String companyId;
	
	@Column(name="ERRORCODE")
    private Integer errorCode;
	
	@Column(name="ERRORDESCRIPTION")
    private String errorDescription;
	
	@Column(name="PODATE")
    private Date poDate;
	
	@Column(name="PONBR")
    private String poNumber;
	
	@Column(name="TERMS")
    private String terms;
	
	@Column(name="VENDID")
    private String vendorId;

	@Column(name="CRTD_USER")
    private String creationUser;
	
	@Column(name="CRTD_DATE")
    private Date creationDate;
	
	@Column(name="TSTAMP")
    private Date timestamp;
	
	@Column(name="STATUS")
    private String status;

	public Integer getBbRefNumber() {
		return bbRefNumber;
	}

	public void setBbRefNumber(Integer bbRefNumber) {
		this.bbRefNumber = bbRefNumber;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public Date getPoDate() {
		return poDate;
	}

	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
