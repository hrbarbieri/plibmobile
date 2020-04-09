package com.brightstar.plibmobi.model.view;

import java.io.Serializable;

public class RequestReceived implements Serializable {
	
	/** */
	private static final long serialVersionUID = 1L;

	private String serialNumber;
	
	private Integer damage;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Integer getDamage() {
		return damage;
	}

	public void setDamage(Integer damage) {
		this.damage = damage;
	}
	
}
