package com.storyagora.web;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.storyagora.domain.Story;
import com.storyagora.domain.User;
import com.storyagora.repositories.StoryRepository;

@Controller
public class StoryController {
	
	@Autowired
	private StoryRepository storyRepo;
	
	@GetMapping("/stories")
	public String getStories(ModelMap model) {
		return "story";
	}
	
	@GetMapping("stories/{storyID}")
	public String getStory(@PathVariable Long storyID, ModelMap model, HttpServletResponse response) throws IOException {
		Optional<Story> storyOpt = storyRepo.findById(storyID);
		
		if (storyOpt.isPresent()) {
			Story story = storyOpt.get();
			model.put("story", story);
		} else {
			response.sendError(HttpStatus.NOT_FOUND.value(), "Oops, we couldn't find a story with that ID.");
			return "story";
		}
		return "story";
	}
	
	@PostMapping("/stories")
	public String createStory(@AuthenticationPrincipal User user) {
		
		Story story = new Story();
		story.setUser(user);
		story = storyRepo.save(story);
		
		return "redirect:/stories/" + story.getId();
	}
}
