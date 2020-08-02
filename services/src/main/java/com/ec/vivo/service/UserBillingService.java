package com.ec.vivo.service;


import org.springframework.stereotype.Service;

import java.util.List;

import com.ec.vivo.domain.UserBilling;
import com.ec.vivo.repository.UserBillingDbRepository;



@Service
public class UserBillingService {

	//@Autowired
	private UserBillingDbRepository billingRepository;
	
	public UserBillingService(UserBillingDbRepository billingRepository) {
		this.billingRepository = billingRepository;
	}
	
	public List<UserBilling> getAllByEmail(String email){
		return billingRepository.findAllByEmail(email);
	}
	
	public List<UserBilling> getByEmail(String email){
		return billingRepository.findByEmail(email);
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	 public UserBilling saveBilling(UserBilling billing) {

		 return billingRepository.save(billing);
	 
	 }

}
