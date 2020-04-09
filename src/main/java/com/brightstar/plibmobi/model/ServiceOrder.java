package com.brightstar.plibmobi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.brightstar.plibmobi.util.ProcessStep;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SERVICE_ORDER", schema = "RMAAPROWN")
@JsonIgnoreProperties({"country", "historys", "units", "hibernateLazyInitializer", "handler"})
public class ServiceOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SERVICE_ORDER_ID")
	@SequenceGenerator(name="SERVICE_ORDER_SEQ", schema="RMAAPROWN")
	private Integer id;

	@Column(name = "SERIAL_NUMBER")
	private String serialNumber;
	
	@OneToOne(targetEntity=Model.class, fetch=FetchType.LAZY)
	@JoinColumn(name = "MODEL_ID")
	private Model model;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PROCESS_STEP")
	private ProcessStep processStep; 
	
	@OneToMany(mappedBy="serviceOrder", fetch=FetchType.LAZY)
	@OrderBy("createTimestamp ASC")
	private List<ServiceOrderHistory> historys;
	
	@OneToOne(targetEntity=Sources.class, fetch=FetchType.LAZY)
	@JoinColumn(name = "SOURCE_ID")
	private Sources source;
	
	@OneToOne(targetEntity=Country.class, fetch=FetchType.LAZY)
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;
	
	@Column(name = "ACCESS_KEY_NF")
	private String accessKeyNf;
	
	private String active;
	
	@Column(name = "CREATE_USER_ID")
	private String createUserId;
	
	@Column(name = "CREATE_TIMESTAMP")
	private Date createTimestamp;
	
	@Column(name = "UPDATE_USER_ID")
	private String updateUserId;
	
	@Column(name = "UPDATE_TIMESTAMP")
	private Date updateTimestamp;
	
	
	@OneToMany(mappedBy="serviceOrder", fetch=FetchType.LAZY)
	private List<TransactionUnit> units;
	
	@OneToMany(mappedBy="serviceOrder", fetch=FetchType.EAGER)
	private List<ServiceOrderBbeMpx> orderBbeMpx;
	
	@OneToOne(mappedBy="serviceOrder", fetch=FetchType.LAZY)
	private ServiceOrderBuyback orderBuyback;
	
	
	public ServiceOrder() {
	}

	public ServiceOrder(Integer id) {
		super();
		this.id = id;
	}

	public List<TransactionUnit> getUnits() {
		return units;
	}

	public void setUnits(List<TransactionUnit> units) {
		this.units = units;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public ProcessStep getProcessStep() {
		return processStep;
	}

	public void setProcessStep(ProcessStep processStep) {
		this.processStep = processStep;
	}

	public Sources getSource() {
		return source;
	}

	public void setSource(Sources source) {
		this.source = source;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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

	public String getAccessKeyNf() {
		return accessKeyNf;
	}

	public void setAccessKeyNf(String accessKeyNf) {
		this.accessKeyNf = accessKeyNf;
	}

	public List<ServiceOrderHistory> getHistorys() {
		return historys;
	}

	public void setHistorys(List<ServiceOrderHistory> historys) {
		this.historys = historys;
	}

	public List<ServiceOrderBbeMpx> getOrderBbeMpx() {
		return orderBbeMpx;
	}

	public void setOrderBbeMpx(List<ServiceOrderBbeMpx> orderBbeMpx) {
		this.orderBbeMpx = orderBbeMpx;
	}

	public ServiceOrderBuyback getOrderBuyback() {
		return orderBuyback;
	}

	public void setOrderBuyback(ServiceOrderBuyback orderBuyback) {
		this.orderBuyback = orderBuyback;
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
