package com.brightstar.plibmobi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="MODEL", schema="RMAAPROWN")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Model implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="MODEL_SEQ", schema="RMAAPROWN")
	@Column(name="MODEL_ID")
	private Integer modelId;
	
	@Column(name="MODEL_NAME")
	private String modelName;
	
	@Column(name="MODEL_DESC")
	private String modelDescriptionString;
	
	private String active;

	@Column(name="CREATE_USER_ID")
	private String createUserId;
	
	@Column(name="CREATE_TIMESTAMP")
	private Date createTimestamp;
	
	@JsonIgnore
	@OneToMany(mappedBy="entityId", fetch=FetchType.LAZY)
	private List<InterfaceXref> xrefs;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CARRIER_ID")
	private Carrier carrier;
	
	
	public Model(Integer modelId) {
		this.modelId = modelId;
	}

	public Model() {
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelDescriptionString() {
		return modelDescriptionString;
	}

	public void setModelDescriptionString(String modelDescriptionString) {
		this.modelDescriptionString = modelDescriptionString;
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

	public List<InterfaceXref> getXrefs() {
		return xrefs;
	}

	public void setXrefs(List<InterfaceXref> xrefs) {
		this.xrefs = xrefs;
	}

}
