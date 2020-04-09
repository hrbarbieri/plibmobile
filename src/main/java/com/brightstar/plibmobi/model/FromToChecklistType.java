package com.brightstar.plibmobi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FROM_TO_CHECKLIST", schema = "RMAAPROWN")
public class FromToChecklistType implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROWID")
	private String id;

	@Column(name="COUNTRY_ID")
	private Integer countryId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CHECKLIST_TYPE_ID_FRONT") 
	private ChecklistType checklistTypeIdFrom;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CHECKLIST_TYPE_ID_BACK")
	private ChecklistType checklistTypeIdBack;
	
	private String active;
	
	@Column(name = "CREATE_USER_ID")
	private String createUserId;
	
	@Column(name = "CREATE_TIMESTAMP")
	private Date createTimestamp;
	
	@Column(name = "UPDATE_USER_ID")
	private String updateUserId;
	
	@Column(name = "UPDATE_TIMESTAMP")
	private Date updateTimestamp;

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
	
	public ChecklistType getChecklistTypeIdFrom() {
		return checklistTypeIdFrom;
	}

	public void setChecklistTypeIdFrom(ChecklistType checklistTypeIdFrom) {
		this.checklistTypeIdFrom = checklistTypeIdFrom;
	}

	public ChecklistType getChecklistTypeIdBack() {
		return checklistTypeIdBack;
	}

	public void setChecklistTypeIdBack(ChecklistType checklistTypeIdBack) {
		this.checklistTypeIdBack = checklistTypeIdBack;
	}

}
