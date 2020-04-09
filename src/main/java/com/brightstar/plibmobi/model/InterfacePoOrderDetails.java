package com.brightstar.plibmobi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="INTERFACE_PO_ORDER_DETAILS", schema="RMAAPROWN")
public class InterfacePoOrderDetails {
	
	@Id
	@Column(name="ROWID")
	private String id;

	@OneToOne(targetEntity=InterfacePoCreationDetails.class, fetch=FetchType.EAGER)
	@JoinColumn(nullable = false, name = "BBLINEREFNBR")
	private InterfacePoCreationDetails bbLineRefNbr;
	
	@Column(name="SERVICE_ORDER_ID")
    private Integer serviceOrderId;
    
	@Column(name="SERIAL_NUMBER")
    private String serialNumber;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public InterfacePoCreationDetails getBbLineRefNbr() {
		return bbLineRefNbr;
	}

	public void setBbLineRefNbr(InterfacePoCreationDetails bbLineRefNbr) {
		this.bbLineRefNbr = bbLineRefNbr;
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
	
}
