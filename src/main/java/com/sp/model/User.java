package com.sp.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



@Entity
@Table(name="User")

public class User {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name = "userid", unique = true, nullable = false)
private int userId;

@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$" , message = "error.email.pattern")
@NotNull(message = "error.email.notnull")
@Column(name = "email", length = 255)
private String email;


@Column(name = "password", length = 255)
private String password;

@NotNull(message = "error.name.notnull")
@Column(name = "name", length = 100)
private String name;

@Column(name = "contactNo", length = 20)
private long contactNo;

@Column(name = "deviceToken", length = 255)
private String deviceToken ;

@Column(name = "deviceType", length = 1)
private String deviceType;

@Column(name = "hashpassword", length = 255)
private String hashPassword;

@Column(name = "pin", length = 255)
private String pin;


@Column(name = "googleId",unique = true, length = 255)
private String googleId;

@Column(name = "IsDeleted", columnDefinition = "tinyint(1) default 0")
private Boolean IsDeleted= Boolean.FALSE;

@Column(name = "IsActive", columnDefinition = "tinyint(0) default 1")
private Boolean IsActive= Boolean.TRUE;


public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public long getContactNo() {
	return contactNo;
}

public void setContactNo(long contactNo) {
	this.contactNo = contactNo;
}

public String getDeviceToken() {
	return deviceToken;
}

public void setDeviceToken(String deviceToken) {
	this.deviceToken = deviceToken;
}

public String getDeviceType() {
	return deviceType;
}

public void setDeviceType(String deviceType) {
	this.deviceType = deviceType;
}

public String getHashPassword() {
	return hashPassword;
}

public void setHashPassword(String hashPassword) {
	this.hashPassword = hashPassword;
}

public String getPin() {
	return pin;
}

public void setPin(String pin) {
	this.pin = pin;
}

public String getGoogleId() {
	return googleId;
}

public void setGoogleId(String googleId) {
	this.googleId = googleId;
}

public Boolean getIsDeleted() {
	return IsDeleted;
}

public void setIsDeleted(Boolean isDeleted) {
	IsDeleted = isDeleted;
}

public Boolean getIsActive() {
	return IsActive;
}

public void setIsActive(Boolean isActive) {
	IsActive = isActive;
}





}

