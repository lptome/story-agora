package com.storyagora.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storyagora.domain.Story;
import com.storyagora.domain.User;


public interface StoryRepository extends JpaRepository<Story, Long> {
	
	List<Story> findByUser(User user);
	
}
