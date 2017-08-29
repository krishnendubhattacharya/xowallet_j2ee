package com.sp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Login {

	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$" , message = "error.email.pattern")
	@NotNull(message = "error.email.notnull")	
	private String email;
	
	//@NotNull(message ="error.password.notnull")	
	private String password;
	
	@NotNull(message ="error.deviceToken.notnull")	
	private String deviceToken ;
	
	@NotNull(message ="error.deviceType.notnull")	
	private String deviceType;
	
	
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
	
	
	

}
