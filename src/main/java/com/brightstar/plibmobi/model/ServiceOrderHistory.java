package com.brightstar.plibmobi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brightstar.plibmobi.util.ProcessStep;


@Entity
@Table(name="SERVICE_ORDER_HISTORY", schema="RMAAPROWN")
public class ServiceOrderHistory implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="SERVICE_ORDER_HISTORY_ID")
	private Integer serviceOrderHistoryId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SERVICE_ORDER_ID")
	private ServiceOrder serviceOrder;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PROCESS_STEP")
	private ProcessStep processStep;
	
	@Column(name="CREATE_USER_ID")
	private String createUserId;
	
	@Column(name="CREATE_TIMESTAMP")
	private Date createTimestamp;
	
	@Column(name="STANDING_HERE")
	private Integer standingHere;
	
	@Column(name="TRANSACTION_TIMESTAMP")
	private Date transactionTimestamp;

	
	public Integer getServiceOrderHistoryId() {
		return serviceOrderHistoryId;
	}

	public void setServiceOrderHistoryId(Integer serviceOrderHistoryId) {
		this.serviceOrderHistoryId = serviceOrderHistoryId;
	}

	public ProcessStep getProcessStep() {
		return processStep;
	}

	public void setProcessStep(ProcessStep processStep) {
		this.processStep = processStep;
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

	public Integer getStandingHere() {
		return standingHere;
	}

	public void setStandingHere(Integer standingHere) {
		this.standingHere = standingHere;
	}

	public Date getTransactionTimestamp() {
		return transactionTimestamp;
	}

	public void setTransactionTimestamp(Date transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
	}

}
