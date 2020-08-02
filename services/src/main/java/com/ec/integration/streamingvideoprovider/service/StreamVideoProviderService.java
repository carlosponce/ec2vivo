package com.ec.integration.streamingvideoprovider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.integration.streamingvideoprovider.message.dto.ResponseMsgDto;
import com.ec.integration.streamingvideoprovider.message.dto.UserBillingDto;
import com.ec.integration.streamingvideoprovider.message.dto.UserEc2VivoDto;
import com.ec.vivo.domain.UserBilling;
import com.ec.vivo.domain.UserEc2Vivo;
import com.ec.vivo.service.UserBillingService;
import com.ec.vivo.service.UserEc2VivoService;

@Service
public class StreamVideoProviderService {

	@Autowired
	UserBillingService billingService;

	@Autowired
	UserEc2VivoService userService;

	public ResponseMsgDto saveBilling(UserBilling billing) {
		ResponseMsgDto response = new ResponseMsgDto();
		UserBillingDto ubd = new UserBillingDto();

		ubd.setBilling(billingService.saveBilling(billing));

		if (ubd.getBilling() != null) {
			response.setResult("00");
		} else {
			response.setResult("ERROR");
		}
		
		response.setBilling(ubd);

		return response;
	}

	public ResponseMsgDto getBillings(String email) {

		ResponseMsgDto response = new ResponseMsgDto();

		UserBillingDto ubd = new UserBillingDto();
		ubd.setBillingList(billingService.getByEmail(email));

		if (ubd.getBillingList() != null && !ubd.getBillingList().isEmpty()) {
			response.setResult("00");
		} else {
			response.setResult("ERROR");
		}
		
		response.setBilling(ubd);

		return response;
	}

	public ResponseMsgDto saveUser(UserEc2Vivo user) {
		ResponseMsgDto response = new ResponseMsgDto();

		UserEc2VivoDto u2v = new UserEc2VivoDto();
		u2v.setUser(userService.saveUser(user));

		if (u2v.getUser() != null) {
			response.setResult("00");
		} else {
			response.setResult("ERROR");
		}

		response.setUser(u2v);
		return response;
	}
}
