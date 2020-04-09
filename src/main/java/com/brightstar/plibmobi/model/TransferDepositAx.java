package com.brightstar.plibmobi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TRANSFER_DEPOSIT_AX", schema = "RMAAPROWN")
public class TransferDepositAx {

	@Id
	@SequenceGenerator(sequenceName="TRANSFER_DEPOSIT_AX_SEQ", schema="RMAAPROWN", allocationSize = 1, name = "TAAX_SEQ")
	@GeneratedValue(generator="TAAX_SEQ", strategy=GenerationType.AUTO)	
	private Long id;
	
	@Column(name="SERVICE_ORDER_ID")
	private Integer serviceOrderId;
	
	@Column(name="SERIAL_NUMBER")
	private String serialNumber;
	
	private String ponbr;
	
	@Column(name="INVENT_LOCATION_TO")
	private String inventLocationId;
	
	@Column(name="WMS_LOCATION_ID")
	private String wmsLocationId;
	
	@Column(name="PLIB_REFERENCE")
	private String plibReference;
	
	private String status;
	
	@Column(name="ERROR_DESCRIPTION")
	private String errorDescription;
	
	@Column(name="TYPE_TRANSFER")
	private String typeTransfer;

	@Column(name="CREATE_USER_ID")
	private String createUserId;
	
	@Column(name="CREATE_TIMESTAMP")
	private Date createTimestamp;
	
	@Column(name="UPDATE_USER_ID")
	private String updateUserId;
	
	@Column(name="UPDATE_TIMESTAMP")
	private Date updateTimestamp;

	private String request;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getServiceOrderId() {
		return serviceOrderId;
	}

	public void setServiceOrderId(Integer serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getPonbr() {
		return ponbr;
	}

	public void setPonbr(String ponbr) {
		this.ponbr = ponbr;
	}

	public String getInventLocationId() {
		return inventLocationId;
	}

	public void setInventLocationId(String inventLocationId) {
		this.inventLocationId = inventLocationId;
	}

	public String getWmsLocationId() {
		return wmsLocationId;
	}

	public void setWmsLocationId(String wmsLocationId) {
		this.wmsLocationId = wmsLocationId;
	}

	public String getPlibReference() {
		return plibReference;
	}

	public void setPlibReference(String plibReference) {
		this.plibReference = plibReference;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String getTypeTransfer() {
		return typeTransfer;
	}

	public void setTypeTransfer(String typeTransfer) {
		this.typeTransfer = typeTransfer;
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

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}
	
	
}
