package com.brightstar.plibmobi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="PROVIDER", schema="RMAAPROWN")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Provider implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PROVIDER_ID")
	@SequenceGenerator(name="PROVIDER_SEQ", schema="RMAAPROWN")
	private Integer providerId;
	
	@Column(name="PROVIDER_NAME")
	private String providerName;
	
	@Column(name="PROVIDER_TYPE")
	private String providerType;
	
	@Column(name="PROVIDER_DESC")
	private String providerDesc;
	
	private String active; 
	
	@OneToOne(targetEntity=Country.class, fetch=FetchType.LAZY)
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;
	
	@Column(name="CREATE_USER_ID")
	private String createUserId;
	
	@Column(name="CREATE_TIMESTAMP")
	private Date createTimestamp;
	
	@Column(name="UPDATE_USER_ID")
	private String updateUserId;
	
	@Column(name="UPDATE_TIMESTAMP")
	private Date updateTimestamp;

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderType() {
		return providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	public String getProviderDesc() {
		return providerDesc;
	}

	public void setProviderDesc(String providerDesc) {
		this.providerDesc = providerDesc;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}


}
