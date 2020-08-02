package com.ec.vivo.repository;

import com.ec.vivo.domain.UserEc2Vivo;



import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB reactive repository for the UserEc2Vivo entity.
 */

@Repository
public interface UserEc2VivoRepository extends ReactiveMongoRepository<UserEc2Vivo, String> {

	

}
