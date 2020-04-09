package com.brightstar.plibmobi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "OPERATOR", schema = "RMAAPROWN")
public class Operator implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(sequenceName="OPERATOR_SEQ", schema="RMAAPROWN", allocationSize = 1, name = "OP_SEQ")
	@GeneratedValue(generator="OP_SEQ", strategy=GenerationType.AUTO)
	private Integer operatorId;
	
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="COUNTRY_ID")
	private Country country;
	
	private String name;
	
	@Column(name="EXTERNAL_REFERENCE")
	private String externalReference;
	
	@Column(name="SHORT_NAME")
	private String shortName;
	
	private Integer unlocked;
	
	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExternalReference() {
		return externalReference;
	}

	public void setExternalReference(String externalReference) {
		this.externalReference = externalReference;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getUnlocked() {
		return unlocked;
	}

	public void setUnlocked(Integer unlocked) {
		this.unlocked = unlocked;
	}
	
}
