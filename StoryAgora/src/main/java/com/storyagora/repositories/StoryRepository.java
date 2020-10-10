package com.storyagora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storyagora.domain.Story;

public interface StoryRepository extends JpaRepository<Story, Long> {

}
