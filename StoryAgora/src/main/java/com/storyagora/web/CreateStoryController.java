package com.storyagora.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.storyagora.domain.Story;
import com.storyagora.domain.User;
import com.storyagora.repositories.StoryRepository;

@Controller
public class CreateStoryController {

	@Autowired
	private StoryRepository storyRepo;

	@GetMapping("/createStory")
	public String getView(@AuthenticationPrincipal User user, ModelMap model) {
		model.put("user", user);
		model.put("story", new Story());
		return "createStory";
	}

	@PostMapping("/createStory")
	public String createStory(@AuthenticationPrincipal User user, Story story) {
		story.setUser(user);
		storyRepo.save(story);
		return "redirect:/story/" + story.getId();

	}
}