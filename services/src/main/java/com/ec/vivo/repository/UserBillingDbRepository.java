package com.ec.vivo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ec.vivo.domain.UserBilling;

@Repository
public interface UserBillingDbRepository extends MongoRepository<UserBilling, String> {

	public List<UserBilling> findAllByEmail(String email);
	public List<UserBilling> findByEmail(String email);
}
