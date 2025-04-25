package com.springOAuthJWTServer.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springOAuthJWTServer.entity.UserInfoEntity;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfoEntity, Long>{

	Optional<UserInfoEntity> findByEmailId(String emailId);
	
}
