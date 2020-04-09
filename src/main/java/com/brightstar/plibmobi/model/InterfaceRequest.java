package com.brightstar.plibmobi.model;

import java.sql.Blob;
import java.sql.SQLException;
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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.brightstar.plibmobi.util.InterfaceRequestFlow;

@Entity
@Table(name="INTERFACE_REQUEST", schema="RMAAPROWN")
public class InterfaceRequest {
	
	@Id
	@Column(name="TRANSACTION")
	@SequenceGenerator(sequenceName="INTERFACE_SEQ", schema="RMAAPROWN", allocationSize = 1, name = "INTER_SEQ")
	@GeneratedValue(generator="INTER_SEQ", strategy=GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(targetEntity=Country.class, fetch=FetchType.LAZY)
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;
	
	@OneToMany(mappedBy="transactionId", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<InterfaceError> errors;
	
	private Integer request;
	
	@Lob
	private Blob bean;
	
	@Enumerated(EnumType.STRING)
	private InterfaceRequestFlow flow;
	
	private String instance;
	
	@Column(name="CLIENT_HOST")
	private String clientHost;
	
	private String active;
	
	@Column(name="UPLOAD_USER")
	private String uploadUser;
	
	@Column(name="UPLOAD_DATE")
	private Date uploadDate;
	
	
	private static String DEFAULT_CLIENT_HOST;
	
	static {
        try {
            DEFAULT_CLIENT_HOST = java.net.InetAddress.getLocalHost().getHostName();
        } catch (Exception err) {
            System.err.print("com.brightstar.plibmobi.model.InterfaceRequest: Can't java.net.InetAddress.getLocalHost().getHostName()");
        }
        if ((DEFAULT_CLIENT_HOST == null)||(DEFAULT_CLIENT_HOST.isEmpty())) {
            try {
                DEFAULT_CLIENT_HOST = System.getenv("HOSTNAME");
                if ((DEFAULT_CLIENT_HOST == null)||(DEFAULT_CLIENT_HOST.isEmpty())) {
                    DEFAULT_CLIENT_HOST = System.getenv("COMPUTERNAME");
                }
            } catch (Exception err) {
                System.err.print("com.brightstar.plibmobi.model.InterfaceRequest: Can't System.getenv('HOSTNAME') or System.getenv('COMPUTERNAME')");
            }
	    }
	}

	
	public InterfaceRequest() {
	}


	public InterfaceRequest(Country country, String bean, String user, InterfaceRequestFlow flow, String instance, Date uploadDate) throws SerialException, SQLException {
		Blob blob = new SerialBlob(bean.getBytes());
		setErrors(new ArrayList<InterfaceError>());
		setClientHost(DEFAULT_CLIENT_HOST);
		setCountry(country);
		setId(0);
		setRequest(0);
		setBean(blob);
		setFlow(flow);
		setUploadDate(uploadDate != null ? uploadDate : new Date());
		setInstance(instance);
		setUploadUser(user);
	}
	
	
	public Blob getBean() {
		return bean;
	}

	public void setBean(Blob bean) {
		this.bean = bean;
	}

	public List<InterfaceError> getErrors() {
		return errors;
	}

	public void setErrors(List<InterfaceError> errors) {
		this.errors = errors;
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

	public Integer getRequest() {
		return request;
	}

	public void setRequest(Integer request) {
		this.request = request;
	}

	public InterfaceRequestFlow getFlow() {
		return flow;
	}

	public void setFlow(InterfaceRequestFlow flow) {
		this.flow = flow;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public String getClientHost() {
		return clientHost;
	}

	public void setClientHost(String clientHost) {
		this.clientHost = clientHost;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

}
