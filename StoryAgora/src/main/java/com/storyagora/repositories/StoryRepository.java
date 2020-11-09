package com.storyagora.repositories;

import java.util.SortedSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.storyagora.domain.Story;
import com.storyagora.domain.User;

public interface StoryRepository extends JpaRepository<Story, Long> {

	public SortedSet<Story> findByUser(User user);

	@Query("SELECT s from Story s WHERE s.title LIKE %?1%")
	public SortedSet<Story> findByKeyword(String keyword);

	@Query(nativeQuery = true, value = "SELECT * from storyagora.story WHERE created_date BETWEEN NOW() - INTERVAL 30 DAY AND NOW()")
	public SortedSet<Story> findRecent();

}
