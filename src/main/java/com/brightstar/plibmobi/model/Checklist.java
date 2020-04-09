package com.brightstar.plibmobi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CHECKLIST", schema="RMAAPROWN")
public class Checklist implements Comparable<Checklist>{
	
	@Id
	@Column(name="CHECKLIST_ID")
	@SequenceGenerator(sequenceName="CHECKLIST_SEQ", schema="RMAAPROWN", allocationSize = 1, name = "CHL_SEQ")
	@GeneratedValue(generator="CHL_SEQ", strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name;

	@Column(name="CHECKLIST_GROUP_ID")
	private Integer groupId;
	
	@OneToMany(mappedBy="checklistId", fetch=FetchType.EAGER)
	private List<ChecklistType> checkTypes;
	
	private String active;
	
	@OneToOne(targetEntity=Country.class, fetch=FetchType.LAZY)
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;
	
	@Column(name="ORDERBY")
	private Integer orderBy;
	
	@Column(name="CHECKLIST_IDENTIFIER")
	private String checklistIdentifier;
	
	@Column(name = "CREATE_USER_ID")
	private String createUserId;
	
	@Column(name = "CREATE_TIMESTAMP")
	private Date createTimestamp;
	
	@Column(name = "UPDATE_USER_ID")
	private String updateUserId;
	
	@Column(name = "UPDATE_TIMESTAMP")
	private Date updateTimestamp;
	
	private Integer checked;
	
	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
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

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getChecklistIdentifier() {
		return checklistIdentifier;
	}

	public void setChecklistIdentifier(String checklistIdentifier) {
		this.checklistIdentifier = checklistIdentifier;
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

	public List<ChecklistType> getCheckTypes() {
		return checkTypes;
	}

	public void setCheckTypes(List<ChecklistType> checkTypes) {
		this.checkTypes = checkTypes;
	}

	@Override
	public int compareTo(Checklist o) {
		return this.getName().compareTo(o.getName());  
	}
	
}
