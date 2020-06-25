package com.ec.vivo.repository;

import com.ec.vivo.domain.UserBilling;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB reactive repository for the UserBilling entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserBillingRepository extends ReactiveMongoRepository<UserBilling, String> {


}
