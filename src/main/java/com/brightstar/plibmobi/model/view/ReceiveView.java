package com.brightstar.plibmobi.model.view;

import com.brightstar.plibmobi.model.dto.ServiceOrderDto;

public class ReceiveView {
	
	private Integer sla;
	
	private Integer damage;
	
	private String messageError;
	
	private Integer validAccessKey;
	
	private ServiceOrderDto serviceOrderDto;
	

	public ReceiveView() {
	}

	public ReceiveView(String messageError, Integer validAccessKey, ServiceOrderDto serviceOrderDto) {
		super();
		this.messageError = messageError;
		this.validAccessKey = validAccessKey;
		this.serviceOrderDto = serviceOrderDto;
	}

	public Integer getSla() {
		return sla;
	}

	public void setSla(Integer sla) {
		this.sla = sla;
	}
	
	public Integer getDamage() {
		return damage;
	}

	public void setDamage(Integer damage) {
		this.damage = damage;
	}

	public ServiceOrderDto getServiceOrderDto() {
		return serviceOrderDto;
	}

	public void setServiceOrderDto(ServiceOrderDto serviceOrderDto) {
		this.serviceOrderDto = serviceOrderDto;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	public Integer getValidAccessKey() {
		return validAccessKey;
	}

	public void setValidAccessKey(Integer validAccessKey) {
		this.validAccessKey = validAccessKey;
	}

	@Override
	public String toString() {
		return "ReceiveView [sla=" + sla + ", damage=" + damage + ", messageError=" + messageError + ", validAccessKey="
				+ validAccessKey + ", serviceOrderDto=" + serviceOrderDto + "]";
	}
	
	

}
