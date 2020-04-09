package com.brightstar.plibmobi.security.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROFILE", schema = "PARAMOWN_APR") //PROD schema="PARAMOWN" | UAT schema="PARAMOWN_APR"
public class Role {

	@Id
	@Column(name="PROFILE_ID")
	private Integer profileId;
	
	@Column(name="PROFILE_NAME")
	private String name;
	
	@Column(name="PROFILE_DESC")
	private String profileDesc;
	
	@Column(name="CREATE_USER_ID")
	private String createuserId;
	
	@Column(name="CREATE_DATETIME")
	private Date createDatetime;
	
    @ManyToMany(mappedBy = "roles")
    private Collection<Users> users;


    public Role() {
        super();
    }

    public Role(final String name) {
        super();
        this.name = name;
    }

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}


	public String getProfileDesc() {
		return profileDesc;
	}

	public void setProfileDesc(String profileDesc) {
		this.profileDesc = profileDesc;
	}

	public String getCreateuserId() {
		return createuserId;
	}

	public void setCreateuserId(String createuserId) {
		this.createuserId = createuserId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Collection<Users> getUsers() {
		return users;
	}

	public void setUsers(Collection<Users> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}