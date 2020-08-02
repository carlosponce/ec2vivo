package com.ec.integration.streamingvideoprovider.message.dto;

import java.io.Serializable;

public class ResponseMsgDto implements Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -7163698368417345034L;
	private String result;
	private UserEc2VivoDto user;
	private UserBillingDto billing;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
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
	
	
}
