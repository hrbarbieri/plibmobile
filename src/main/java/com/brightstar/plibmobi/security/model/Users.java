package com.brightstar.plibmobi.security.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="USERS", schema="PARAMOWN_APR") //PROD schema="PARAMOWN" | UAT schema="PARAMOWN_APR"
public class Users {
	
	@Id
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="USER_PASSWORD")
	private String password;
	
	@Column(name="ATTEMPT_UNSUCCESSFUL")
	private Integer attemptUnsuccesful;
	
	@Column(name="USER_STATUS")
	private String userStatus;
	
	@Column(name="EMAIL")
	private String email;
	
	
	@Column(name="LAST_LOGIN_DATETIME")
	private Date lastLogin;
	
	@Column(name="PWD_EXPIRE_DATE")
	private Date pwdExpire;
	
	@Column(name="CREATE_USER_ID")
	private String createUserId;
	
	@Column(name="CREATE_DATETIME")
	private Date createDatetime;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_PROFILE", schema="PARAMOWN_APR", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "PROFILE_ID", referencedColumnName = "PROFILE_ID"))
    private Collection<Role> roles;
	
	public String getUserNameReduced() {
		return getUserId().length() > 9 ?  getUserId().substring(0, 9)+"..." : getUserId();
	}
	public Integer getAttemptUnsuccesful() {
		return attemptUnsuccesful;
	}
	public void setAttemptUnsuccesful(Integer attemptUnsuccesful) {
		this.attemptUnsuccesful = attemptUnsuccesful;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Date getPwdExpire() {
		return pwdExpire;
	}
	public void setPwdExpire(Date pwdExpire) {
		this.pwdExpire = pwdExpire;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

}
