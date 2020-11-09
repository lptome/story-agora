package com.storyagora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storyagora.domain.Story;
import com.storyagora.repositories.StoryRepository;

@Service
public class StoryService {

	@Autowired
	private StoryRepository storyRepo;

	public Story save(Story story) {

		return storyRepo.save(story);
	}

	public Story edit(Story oldStory, Story story) {

		// Create new Story object
		Story newStory = new Story();

		// Assign details to this new object.
		newStory.setId(oldStory.getId());
		newStory.setUser(oldStory.getUser());
		newStory.setComments(oldStory.getComments());
		newStory.setCreatedDate(oldStory.getCreatedDate());
		newStory.setContent(story.getContent());
		newStory.setSummary(story.getSummary());
		newStory.setTitle(story.getTitle());

		return newStory;

	}

}
