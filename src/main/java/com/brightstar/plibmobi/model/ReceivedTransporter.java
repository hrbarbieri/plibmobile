package com.brightstar.plibmobi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="RECEIVED_TRANSPORTER", schema="RMAAPROWN")
public class ReceivedTransporter {
	
	@Id
	@SequenceGenerator(sequenceName="RECEIVED_TRANSP_SEQ", schema="RMAAPROWN", allocationSize = 1, name = "RT_SEQ")
	@GeneratedValue(generator="RT_SEQ", strategy=GenerationType.AUTO)
	private Integer id;
	
	/*
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "SERVICE_ORDER_ID")
	private ServiceOrder serviceOrder;
	*/
	
	@Column(name="SERVICE_ORDER_ID")
	private Integer serviceOrderId;
	
	/*
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "PROVIDER_ID")
    private Provider provider;
	*/
	
	@Column(name="PROVIDER_ID")
	private Integer providerId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="RECEIVE_TYPE")
    private ReceivedTransporterType receiveType;
	
	@Column(name="TIER_BBE")
    private Integer tierBbe;
	
	@Column(name="COLLECTED_MALFUNCTIONS")
    private String collectedMalfunctions;
	
	@Column(name = "DISCREPANCY_TRANSPORTER")
    private String discrepancyTransporter;
    
    @Column(name = "CREATE_USER_ID")
	private String createUserId;
	
	@Column(name = "CREATE_TIMESTAMP")
	private Date createTimestamp;
    
    
    public enum ReceivedTransporterType {
        UNDAMAGED(1),
        MALFUNCTIONS(2);
        
        private Integer type;

        ReceivedTransporterType(Integer type) {
            this.type = type;
        }
        
        public static ReceivedTransporterType findTypeBySelected(Integer val) {
            for(ReceivedTransporterType type : ReceivedTransporterType.values()) {
                if(val.intValue() == type.getType().intValue()){
                    return type;
                }
            }
            return null;
        }
        
        public static ReceivedTransporterType findType(String val) {
            for(ReceivedTransporterType type : ReceivedTransporterType.values()) {
                if(val.equals(type.name())){
                    return type;
                }
            }
            return null;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }
    }


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getServiceOrderId() {
		return serviceOrderId;
	}

	public void setServiceOrderId(Integer serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public ReceivedTransporterType getReceiveType() {
		return receiveType;
	}


	public void setReceiveType(ReceivedTransporterType receiveType) {
		this.receiveType = receiveType;
	}


	public Integer getTierBbe() {
		return tierBbe;
	}


	public void setTierBbe(Integer tierBbe) {
		this.tierBbe = tierBbe;
	}


	public String getCollectedMalfunctions() {
		return collectedMalfunctions;
	}


	public void setCollectedMalfunctions(String collectedMalfunctions) {
		this.collectedMalfunctions = collectedMalfunctions;
	}


	public String getDiscrepancyTransporter() {
		return discrepancyTransporter;
	}


	public void setDiscrepancyTransporter(String discrepancyTransporter) {
		this.discrepancyTransporter = discrepancyTransporter;
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
    
}
