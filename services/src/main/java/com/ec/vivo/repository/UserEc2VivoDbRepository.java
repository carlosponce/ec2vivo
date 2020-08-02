package com.ec.vivo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ec.vivo.domain.UserEc2Vivo;

@Repository
public interface UserEc2VivoDbRepository extends MongoRepository<UserEc2Vivo, String> {

	public List<UserEc2Vivo> findAllByEmail(String email);
}
