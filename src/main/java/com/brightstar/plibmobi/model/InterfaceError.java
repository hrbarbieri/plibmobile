package com.brightstar.plibmobi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.brightstar.plibmobi.util.InterfaceErrorType;

@Entity
@Table(name="INTERFACE_ERROR", schema="RMAAPROWN")
public class InterfaceError {
	
	@Id
	@SequenceGenerator(sequenceName="INTERFACE_ERROR_SEQ", schema="RMAAPROWN", allocationSize = 1, name = "ERROR_SEQ")
	@GeneratedValue(generator="ERROR_SEQ", strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="TRANSACTION_ID")
	private Integer transactionId;
	
	private Integer request;

	private Integer error;
	
	private String message;
	
	@Enumerated(EnumType.STRING)
	private InterfaceErrorType type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getRequest() {
		return request;
	}

	public void setRequest(Integer request) {
		this.request = request;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InterfaceErrorType getType() {
		return type;
	}

	public void setType(InterfaceErrorType type) {
		this.type = type;
	}

}
