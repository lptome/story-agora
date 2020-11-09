package com.storyagora.repositories;

import java.util.SortedSet;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storyagora.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	public SortedSet<Comment> findByStoryId(Long Id);
	
}
