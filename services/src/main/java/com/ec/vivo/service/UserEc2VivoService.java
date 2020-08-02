package com.ec.vivo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ec.vivo.domain.UserEc2Vivo;
import com.ec.vivo.repository.UserEc2VivoDbRepository;



@Service
public class UserEc2VivoService {
	
	//@Autowired
	private UserEc2VivoDbRepository userRepository;
	
	public UserEc2VivoService(UserEc2VivoDbRepository userRepository) {
		this.userRepository = userRepository;
	}

	
	public List<UserEc2Vivo> getAllByEmail(String email){
		return userRepository.findAllByEmail(email);
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	 public UserEc2Vivo saveUser(UserEc2Vivo user) {
	     
		 
		 if(user!=null) {
			 
			 if(user.getIsCreation()) {
				 //Check if exist
				 return userRepository.save(user);	 
			 }else {
				 List<UserEc2Vivo> founds = getAllByEmail(user.getEmail());
				 if(founds!=null) {
					 
					 UserEc2Vivo found = founds.get(0);
					 found.setName(user.getName());
					 found.setLastName(user.getLastName());
					 found.setPhone(user.getPhone());
					 
					 
					 return userRepository.save(found);
				 }
				 
				 return null;
			 }
			 
			 
		 }else {
		 
			 return null;
		 }
	}
	 
}
