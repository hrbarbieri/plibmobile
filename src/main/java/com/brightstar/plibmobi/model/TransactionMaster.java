package com.brightstar.plibmobi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.brightstar.plibmobi.util.TransactionMasterFlow;

@Entity
@Table(name="TRANSACTION_MASTER", schema="RMAAPROWN")
public class TransactionMaster implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="TRANSACTION_MASTER_ID")
	@SequenceGenerator(sequenceName="TRANSACTION_MASTER_SEQ", schema="RMAAPROWN", allocationSize = 1, name = "TM_SEQ")
	@GeneratedValue(generator="TM_SEQ", strategy=GenerationType.AUTO)
	private Integer transctionMasterId;
	
	
	@OneToOne(targetEntity=Provider.class)
	@JoinColumn(name = "PROVIDER_ID")
	private Provider provider;
	
	@Enumerated(EnumType.STRING)
	@Column(name="BRIGHTSTAR_SUBSIDIARY")
	private TransactionMasterFlow  brightstarSubsidiary;
	
	@Column(name="PROVIDER_DOC_ID")
	private String providerDocId;
	
	@OneToMany(mappedBy="master", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<TransactionUnit> units;
	
	private String enabled;

	@Column(name="MASTER_BOX")
	private String masterBox;
	
	private String status;
	
	@Column(name="EXTERNAL_REFERENCE")
	private String externalReference;
	
	@Column(name="CREATE_USER_ID")
	private String createUserId;
	
	@Column(name="CREATE_TIMESTAMP")
	private Date createTimestamp;
	
	@Column(name="UPDATE_USER_ID")
	private String updateUserId;
	
	@Column(name="UPDATE_TIMESTAMP")
	private Date updateTimestamp;
	
	
	public void addUnits(TransactionUnit tu) {
		if(units == null) {
			units = new ArrayList<TransactionUnit>();
		}
		units.add(tu);
	}
	

	public Integer getTransctionMasterId() {
		return transctionMasterId;
	}

	public void setTransctionMasterId(Integer transctionMasterId) {
		this.transctionMasterId = transctionMasterId;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public TransactionMasterFlow getBrightstarSubsidiary() {
		return brightstarSubsidiary;
	}

	public void setBrightstarSubsidiary(TransactionMasterFlow brightstarSubsidiary) {
		this.brightstarSubsidiary = brightstarSubsidiary;
	}

	public String getProviderDocId() {
		return providerDocId;
	}

	public void setProviderDocId(String providerDocId) {
		this.providerDocId = providerDocId;
	}

	public List<TransactionUnit> getUnits() {
		return units;
	}

	public void setUnits(List<TransactionUnit> units) {
		this.units = units;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getMasterBox() {
		return masterBox;
	}

	public void setMasterBox(String masterBox) {
		this.masterBox = masterBox;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExternalReference() {
		return externalReference;
	}

	public void setExternalReference(String externalReference) {
		this.externalReference = externalReference;
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
	
}
