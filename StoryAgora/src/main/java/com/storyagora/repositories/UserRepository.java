package com.storyagora.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.storyagora.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUsernameIgnoreCase(String username);
	
	@Query("SELECT u from User u WHERE u.name LIKE %?1%")
	public List<User> findByKeyword(String keyword);
}
