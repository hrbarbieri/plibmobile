package com.brightstar.plibmobi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "TRANSACTION_UNIT", schema = "RMAAPROWN") // , uniqueConstraints={@UniqueConstraint(columnNames =
														// {"serviceOrder","master"})})
@JsonIgnoreProperties({"master"})
public class TransactionUnit implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(sequenceName="TRANSACTION_UNIT_SEQ", schema="RMAAPROWN", allocationSize = 1, name = "TU_SEQ")
	@GeneratedValue(generator="TU_SEQ", strategy=GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "SERVICE_ORDER_ID")
	private ServiceOrder serviceOrder;

	private String notes;

	private String status;

	@Column(name = "ERROR_REASON")
	private String errorReason;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "TRANSACTION_MASTER_ID")
	private TransactionMaster master;

	@Column(name = "CREATE_USER_ID")
	private String createUserId;

	@Column(name = "CREATE_TIMESTAMP")
	private Date createTimestamp;

	@Column(name = "UPDATE_USER_ID")
	private String updateUserId;

	@Column(name = "UPDATE_TIMESTAMP")
	private Date updateTimestamp;

	@Column(name = "RECEIVE_USER_ID")
	private String receiveUserId;

	@Column(name = "RECEIVE_TIMESTAMP")
	private Date receiveTimestamp;

	@Column(name = "SEND_USER_ID")
	private String sendUserId;

	@Column(name = "SEND_TIMESTAMP")
	private Date sendTimestamp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ServiceOrder getServiceOrder() {
		return serviceOrder;
	}

	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

	public TransactionMaster getMaster() {
		return master;
	}

	public void setMaster(TransactionMaster master) {
		this.master = master;
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

	public String getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public Date getReceiveTimestamp() {
		return receiveTimestamp;
	}

	public void setReceiveTimestamp(Date receiveTimestamp) {
		this.receiveTimestamp = receiveTimestamp;
	}

	public String getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}

	public Date getSendTimestamp() {
		return sendTimestamp;
	}

	public void setSendTimestamp(Date sendTimestamp) {
		this.sendTimestamp = sendTimestamp;
	}

}
