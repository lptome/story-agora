package com.storyagora.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.storyagora.domain.Story;
import com.storyagora.domain.User;


public interface StoryRepository extends JpaRepository<Story, Long> {
	
	public List<Story> findByUser(User user);
	
	@Query("SELECT s from Story s WHERE s.title LIKE %?1%")
	public List<Story> findByKeyword(String keyword);
}
