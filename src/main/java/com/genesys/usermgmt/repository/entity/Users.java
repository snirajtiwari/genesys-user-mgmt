package com.genesys.usermgmt.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity(name = "users")
public class Users {

	private Long userId;

	@NotNull(message = "User Name is required")
	private String userName;

	@NotNull(message = "Email Id is required")
	private String emailId;

	private String password;

	private Date lastLoginDate;
	private Date createdDate;
	private Date modifiedDate;

	@Column(name = "userid")
	public Long getUserId() {
		return userId;
	}

	@Id
	@Column(name = "username")
	public String getUserName() {
		return userName;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	@Column(name = "emailid")
	public String getEmailId() {
		return emailId;
	}

	@Column(name = "last_login_date")
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	@Column(name = "modified_date")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
