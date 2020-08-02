package com.ec.integration.streamingvideoprovider.message.dto;

import java.io.Serializable;

public class RequestMsgDto implements Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -1476279023619048394L;
	
	private String code;
	private String email;
	private UserEc2VivoDto user;
	private UserBillingDto billing;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public UserEc2VivoDto getUser() {
		return user;
	}
	public void setUser(UserEc2VivoDto user) {
		this.user = user;
	}
	public UserBillingDto getBilling() {
		return billing;
	}
	public void setBilling(UserBillingDto billing) {
		this.billing = billing;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
