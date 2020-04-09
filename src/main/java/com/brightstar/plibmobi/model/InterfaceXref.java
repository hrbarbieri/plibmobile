package com.brightstar.plibmobi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INTERFACE_XREF", schema="RMAAPROWN")
public class InterfaceXref {
	
	@Id
	@Column(name="ROWID")
	private String id;
	
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	
	private String domain;
	
	private String entity;
	
	@Column(name="ENTITY_ID")
	private Integer entityId;
	
	@Column(name="ENTITY_TEXT_ID")
	private String entityTextId;
	
	@Column(name="EXTERNAL_REFERENCE")
	private String xref;
	
	private Integer priority;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public String getEntityTextId() {
		return entityTextId;
	}

	public void setEntityTextId(String entityTextId) {
		this.entityTextId = entityTextId;
	}

	public String getXref() {
		return xref;
	}

	public void setXref(String xref) {
		this.xref = xref;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
}
