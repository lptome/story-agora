package com.storyagora.web;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

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
import com.storyagora.service.StoryService;

@Controller
public class StoryController {

	@Autowired
	private StoryRepository storyRepo;
	@Autowired
	private StoryService storyService;

	@GetMapping("/story")
	public String redirect() {
		return "redirect:/dashboard";
	}

	@GetMapping("story/{storyID}")
	public String getStory(@AuthenticationPrincipal User user, @PathVariable Long storyID, ModelMap model,
			HttpServletResponse response) throws IOException {
		model.put("user", user);
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

	@GetMapping("story/{storyID}/editStory")
	public String editStory(@AuthenticationPrincipal User user, @PathVariable Long storyID,
			ModelMap model, HttpServletResponse response) throws IOException {
		
		Optional<Story> storyOpt = storyRepo.findById(storyID);
		Story story = new Story();
		
		if (storyOpt.isPresent()) {
			story = storyOpt.get();
			model.put("story", story);
		}
		
		model.put("user", user);
		model.put("story", story);
		return "editStory";
	}

	@PostMapping("story/{storyID}/editStory")
	public String saveChanges(@AuthenticationPrincipal User user, Story story, @PathVariable Long storyID,
			ModelMap model) {

		Optional<Story> storyOpt = storyRepo.findById(storyID);
		Story oldStory = new Story();
		
		if (storyOpt.isPresent()) {
			oldStory = storyOpt.get();
		}
		
		model.put("story", story);
		Story newStory = new Story();
		
		newStory = storyService.edit(oldStory, story);
		storyRepo.save(newStory);

		return "redirect:/story/" + storyID;
	}
}
