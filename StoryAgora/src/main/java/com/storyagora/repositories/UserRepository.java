package com.storyagora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storyagora.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
	
}
