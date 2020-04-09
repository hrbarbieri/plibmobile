package com.brightstar.plibmobi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INTERFACE_CONFIG", schema="RMAAPROWN")
public class InterfaceConfig implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ROWID")
	private String id;
	
	@Column(name="PARAM_GROUP")
	private String group;
	
	@Column(name="PARAM_NAME")
	private String name;
	
	@Column(name="PARAM_VALUE")
	private String value;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

}
