package com.brightstar.plibmobi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SERVICE_ORDER_BUYBACK", schema = "RMAAPROWN")
@JsonIgnoreProperties({"serviceOrder", "hibernateLazyInitializer", "handler"})
public class ServiceOrderBuyback implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ORDER_BUYBACK_SEQ", schema="RMAAPROWN")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "SERVICE_ORDER_ID")
	private ServiceOrder serviceOrder;
	
	@Column(name="EQUIPMENT_TURNS_ON")
	private String equipmentTurnsOn;

	@Column(name="IMEI_CORRECT")
	private String imeiCorrect;

	@Column(name="EQUIPMENT_BLOCKED")
	private String equipmentBlocked;

	@Column(name="CLEAN_MEMORY")
	private String cleanMemory;

	private String discrepancies;
	
	@Column(name="SG_PART_NUMBER")
	private String sgPartNumber;

	@Column(name="SG_GRADING_FINAL")
	private String sgGradingFinal;

	@Column(name="OPERATOR_ID")
	private Integer operatorId;

	@Column(name="MODEL_CORRECT")
	private Integer modelCorrect;
	
	@Column(name="OLD_MODEL_ID")
	private Integer oldModelId;
	
	@Column(name="SG_GRADING_FUNCTIONAL")
	private String sgGradingFunctional;
	
	@Column(name="SG_PARTNUMBER_FUNCTIONAL")
	private String sgPartnumberFunctional;
	
	@Column(name="REGISTERED_BATTERY_CAPACITY")
	private Integer registeredBatteryCapacity;
	
	@Column(name="EXPECTED_BATTERY_CAPACITY")
	private Integer expectedBatteryCapacity;
	
	@Column(name="BATTERY_ACCEPTANCE_PERCENTAGE")
	private Integer batteryAcceptancePercentage;
	
	@Column(name="DISCREPANCY_TYPE")
	private String discrepancyType;

	@Column(name = "CREATE_USER_ID")
	private String createUserId;

	@Column(name = "CREATE_TIMESTAMP")
	private Date createTimestamp;

	@Column(name = "UPDATE_USER_ID")
	private String updateUserId;

	@Column(name = "UPDATE_TIMESTAMP")
	private Date updateTimestamp;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ServiceOrder getServiceOrder() {
		return serviceOrder;
	}

	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

	public String getEquipmentTurnsOn() {
		return equipmentTurnsOn;
	}

	public void setEquipmentTurnsOn(String equipmentTurnsOn) {
		this.equipmentTurnsOn = equipmentTurnsOn;
	}

	public String getImeiCorrect() {
		return imeiCorrect;
	}

	public void setImeiCorrect(String imeiCorrect) {
		this.imeiCorrect = imeiCorrect;
	}

	public String getEquipmentBlocked() {
		return equipmentBlocked;
	}

	public void setEquipmentBlocked(String equipmentBlocked) {
		this.equipmentBlocked = equipmentBlocked;
	}

	public String getCleanMemory() {
		return cleanMemory;
	}

	public void setCleanMemory(String cleanMemory) {
		this.cleanMemory = cleanMemory;
	}

	public String getDiscrepancies() {
		return discrepancies;
	}

	public void setDiscrepancies(String discrepancies) {
		this.discrepancies = discrepancies;
	}

	public String getSgPartNumber() {
		return sgPartNumber;
	}

	public void setSgPartNumber(String sgPartNumber) {
		this.sgPartNumber = sgPartNumber;
	}

	public String getSgGradingFinal() {
		return sgGradingFinal;
	}

	public void setSgGradingFinal(String sgGradingFinal) {
		this.sgGradingFinal = sgGradingFinal;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getModelCorrect() {
		return modelCorrect;
	}

	public void setModelCorrect(Integer modelCorrect) {
		this.modelCorrect = modelCorrect;
	}

	public Integer getOldModelId() {
		return oldModelId;
	}

	public void setOldModelId(Integer oldModelId) {
		this.oldModelId = oldModelId;
	}

	public String getSgGradingFunctional() {
		return sgGradingFunctional;
	}

	public void setSgGradingFunctional(String sgGradingFunctional) {
		this.sgGradingFunctional = sgGradingFunctional;
	}

	public String getSgPartnumberFunctional() {
		return sgPartnumberFunctional;
	}

	public void setSgPartnumberFunctional(String sgPartnumberFunctional) {
		this.sgPartnumberFunctional = sgPartnumberFunctional;
	}

	public Integer getRegisteredBatteryCapacity() {
		return registeredBatteryCapacity;
	}

	public void setRegisteredBatteryCapacity(Integer registeredBatteryCapacity) {
		this.registeredBatteryCapacity = registeredBatteryCapacity;
	}

	public Integer getExpectedBatteryCapacity() {
		return expectedBatteryCapacity;
	}

	public void setExpectedBatteryCapacity(Integer expectedBatteryCapacity) {
		this.expectedBatteryCapacity = expectedBatteryCapacity;
	}

	public Integer getBatteryAcceptancePercentage() {
		return batteryAcceptancePercentage;
	}

	public void setBatteryAcceptancePercentage(Integer batteryAcceptancePercentage) {
		this.batteryAcceptancePercentage = batteryAcceptancePercentage;
	}

	public String getDiscrepancyType() {
		return discrepancyType;
	}

	public void setDiscrepancyType(String discrepancyType) {
		this.discrepancyType = discrepancyType;
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
