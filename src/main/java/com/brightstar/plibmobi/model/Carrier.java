package com.brightstar.plibmobi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CARRIER", schema="RMAAPROWN")
public class Carrier implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="CARRIER_SEQ", schema="RMAAPROWN")
	@Column(name="CARRIER_ID")
	private Integer carrierId;
	
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	
	@Column(name="CARRIER_NAME")
	private String carrierName;
	
	@Column(name="CARRIER_DESC")
	private String carrierDescription;
	
	private String active;
	
	@Column(name="CREATE_USER_ID")
	private String createUserId;
	
	@Column(name="CREATE_TIMESTAMP")
	private Date createTimestamp;
	
	public Integer getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(Integer carrierId) {
		this.carrierId = carrierId;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getCarrierDescription() {
		return carrierDescription;
	}

	public void setCarrierDescription(String carrierDescription) {
		this.carrierDescription = carrierDescription;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

}
