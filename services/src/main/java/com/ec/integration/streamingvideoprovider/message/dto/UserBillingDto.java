package com.ec.integration.streamingvideoprovider.message.dto;

import java.io.Serializable;
import java.util.List;

import com.ec.vivo.domain.UserBilling;
import com.ec.vivo.domain.UserEc2Vivo;

public class UserBillingDto implements Serializable {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 8912301018841997636L;
	List<UserBilling> billingList;
	UserEc2Vivo user;
	UserBilling billing;
	
	public List<UserBilling> getBillingList() {
		return billingList;
	}
	public void setBillingList(List<UserBilling> billingList) {
		this.billingList = billingList;
	}
	public UserEc2Vivo getUser() {
		return user;
	}
	public void setUser(UserEc2Vivo user) {
		this.user = user;
	}
	public UserBilling getBilling() {
		return billing;
	}
	public void setBilling(UserBilling billing) {
		this.billing = billing;
	}
	
	
}
