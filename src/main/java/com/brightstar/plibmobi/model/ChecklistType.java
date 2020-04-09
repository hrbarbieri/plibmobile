package com.brightstar.plibmobi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CHECKLIST_TYPE", schema="RMAAPROWN")
public class ChecklistType implements Comparable<ChecklistType>{
	
	@Id
	@SequenceGenerator(sequenceName="CHECKLIST_TYPE_SEQ", schema="RMAAPROWN", allocationSize = 1, name = "CHT_SEQ")
	@GeneratedValue(generator="CHT_SEQ", strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="CHECKLIST_ID")
	private Integer checklistId;
	
	@Column(name="CHECKLISTTYEPE_DESC")
	private String desc;
	
	private String value;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="RELATION_CHECKLIST_ID") 
	private Checklist relCheck;
	
	private String active;
	
	@Column(name="CHECKLIST_TYPE_IDENTIFIER")
	private String checklistTypeIdentifier;
	
	@Column(name = "IS_OK")
	private String isOk;
	
	@Column(name = "EXTERNAL_REFERENCE")
	private String xref;
	
	@Column(name="ORDERBY")
	private Integer orderBy;
	
	private String code;
	
	@Column(name="CSS_IDENTIFIER")
	private String cssIdentifier;
	
	@Column(name="ORDER_WORST_CASE")
	private Integer orderWorstCase;
	
	@Column(name = "CREATE_USER_ID")
	private String createUserId;
	
	@Column(name = "CREATE_TIMESTAMP")
	private Date createTimestamp;
	
	@Column(name = "UPDATE_USER_ID")
	private String updateUserId;
	
	@Column(name = "UPDATE_TIMESTAMP")
	private Date updateTimestamp;
	
	public ChecklistType() {
	}
	
	public ChecklistType(Integer id) {
		this.id = id;
	}

	@Override
	public int compareTo(ChecklistType o) {
		return this.getDesc().compareTo(o.getDesc());
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getChecklistTypeIdentifier() {
		return checklistTypeIdentifier;
	}

	public void setChecklistTypeIdentifier(String checklistTypeIdentifier) {
		this.checklistTypeIdentifier = checklistTypeIdentifier;
	}

	public String getIsOk() {
		return isOk;
	}

	public void setIsOk(String isOk) {
		this.isOk = isOk;
	}

	public String getXref() {
		return xref;
	}

	public void setXref(String xref) {
		this.xref = xref;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCssIdentifier() {
		return cssIdentifier;
	}

	public void setCssIdentifier(String cssIdentifier) {
		this.cssIdentifier = cssIdentifier;
	}

	public Integer getOrderWorstCase() {
		return orderWorstCase;
	}

	public void setOrderWorstCase(Integer orderWorstCase) {
		this.orderWorstCase = orderWorstCase;
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

	public Integer getChecklistId() {
		return checklistId;
	}

	public void setChecklistId(Integer checklistId) {
		this.checklistId = checklistId;
	}

	public Checklist getRelCheck() {
		return relCheck;
	}

	public void setRelCheck(Checklist relCheck) {
		this.relCheck = relCheck;
	}

}
