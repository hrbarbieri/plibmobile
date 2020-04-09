package com.brightstar.plibmobi.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.brightstar.plibmobi.model.ReceivedTransporter.ReceivedTransporterType;

public class ReceivedTransporterDto implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;
	
	private String[] imeis;
	private String user;
	private Date createDate;
	private Integer providerId;
	private ReceivedTransporterType type;
	private String tierDefault;
	
	private List<ReceivedTransporterParam> parans = new ArrayList<ReceivedTransporterParam>();
	
	public void addParam(Integer serviceOrderId, String partNumber, String collectedBreakdown) {
		parans.add(new ReceivedTransporterParam(serviceOrderId, partNumber, collectedBreakdown));
	}
	
	public String[] getImeis() {
		return imeis;
	}
	public void setImeis(String[] imeis) {
		this.imeis = imeis;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	public ReceivedTransporterType getType() {
		return type;
	}
	public void setType(ReceivedTransporterType type) {
		this.type = type;
	}
	public String getTierDefault() {
		return tierDefault;
	}
	public void setTierDefault(String tierDefault) {
		this.tierDefault = tierDefault;
	}
	public List<ReceivedTransporterParam> getParans() {
		return parans;
	}
	public void setParans(List<ReceivedTransporterParam> parans) {
		this.parans = parans;
	}
}
