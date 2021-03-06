package com.trumpia.dynamics.data;

import org.springframework.data.repository.CrudRepository;

import com.trumpia.dynamics.model.DynamicsAccountEntity;
import com.trumpia.model.UserEntity;

public interface DynamicsAccountRepository extends CrudRepository<DynamicsAccountEntity, Long>{
	DynamicsAccountEntity findOneByResourceUrl(String resourceUrl);
	DynamicsAccountEntity findByUserEntity(UserEntity user);
}