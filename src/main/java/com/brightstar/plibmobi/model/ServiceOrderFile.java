package com.brightstar.plibmobi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="SERVICE_ORDER_FILE", schema="RMAAPROWN")
public class ServiceOrderFile implements Serializable {
	
	/** */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SERVICE_ORDER_FILE_ID")
	private Integer id;
	
	@Column(name="SERVICE_ORDER_ID")
	private Integer serviceOrderId;

	@Lob
    @Column(name="SERVICE_ORDER_FILE", columnDefinition="BLOB")
    private byte[] file;
	
	@Column(name="SERVICE_ORDER_FILE_NAME")
	private String name;
	
	private String category;
	
	private String classification;
	
	private String downloaded;
	
	private String result;
	
	private String active;
	
	@Column(name = "CREATE_USER_ID")
	private String createUserId;
	
	@Column(name = "CREATE_TIMESTAMP")
	private Date createTimestamp;
	
	@Column(name = "UPDATE_USER_ID")
	private String updateUserId;
	
	@Column(name = "UPDATE_TIMESTAMP")
	private Date updateTimestamp;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getServiceOrderId() {
		return serviceOrderId;
	}

	public void setServiceOrderId(Integer serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getDownloaded() {
		return downloaded;
	}

	public void setDownloaded(String downloaded) {
		this.downloaded = downloaded;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

}
