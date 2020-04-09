package com.brightstar.plibmobi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.brightstar.plibmobi.util.CheckListGroupType;

@Entity
@Table(name="CHECKLIST_GROUP", schema="RMAAPROWN")
public class ChecklistGroup {
	
	@Id
	@Column(name="CHECKLIST_GROUP_ID")
	@SequenceGenerator(sequenceName="CHECKLIST_GROUP_SEQ", schema="RMAAPROWN", allocationSize = 1, name = "CHLGRP_SEQ")
	@GeneratedValue(generator="CHLGRP_SEQ", strategy=GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(targetEntity=Country.class, fetch=FetchType.LAZY)
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;
	
	@OneToMany(mappedBy="groupId", fetch=FetchType.EAGER)
	private List<Checklist> checks;
	
	@Column(name="CHECKLIST_GROUP_NAME")
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CHECKLIST_GROUP_TYPE")
	private CheckListGroupType type;
	
	@Column(name = "CREATE_USER_ID")
	private String createUserId;
	
	@Column(name = "CREATE_TIMESTAMP")
	private Date createTimestamp;
	
	@Column(name = "UPDATE_USER_ID")
	private String updateUserId;
	
	@Column(name = "UPDATE_TIMESTAMP")
	private Date updateTimestamp;
	
	private String active;
	
	@Column(name="MODEL_ID")
	private Integer modelId;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Checklist> getChecks() {
		return checks;
	}

	public void setChecks(List<Checklist> checks) {
		this.checks = checks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CheckListGroupType getType() {
		return type;
	}

	public void setType(CheckListGroupType type) {
		this.type = type;
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

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

}
