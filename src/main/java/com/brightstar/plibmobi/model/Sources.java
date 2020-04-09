package com.brightstar.plibmobi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.brightstar.plibmobi.util.SourceType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="SOURCES", schema="RMAAPROWN")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sources implements Serializable {
	
	
	/**  */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="SOURCE_ID")
	@SequenceGenerator(name="SOURCES_SEQ", schema="RMAAPROWN")
	private Integer sourceId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SOURCE_TYPE")
	private SourceType sourceType;
	
	private String deposit;
	
	private String location;
	
	private String active;
	
	@Column(name="UPDATE_USER_ID")
	private String updateUserId;
	
	@Column(name="UPDATE_TIMESTAMP")
	private Date updateTimestamp;
	
	public SourceType getSourceType() {
		return sourceType;
	}
	public void setSourceType(SourceType sourceType) {
		this.sourceType = sourceType;
	}
	public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
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
