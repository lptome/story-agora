package com.storyagora.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.storyagora.domain.Comment;
import com.storyagora.domain.Story;
import com.storyagora.domain.User;
import com.storyagora.repositories.CommentRepository;
import com.storyagora.repositories.StoryRepository;
import com.storyagora.service.StoryService;

@Controller
public class StoryController {

	@Autowired
	private StoryRepository storyRepo;
	@Autowired
	private StoryService storyService;
	@Autowired
	private CommentRepository commentRepo;

	@GetMapping("/story")
	public String redirect() {
		return "redirect:/dashboard";
	}

	@GetMapping("story/{storyId}")
	public String getStory(@AuthenticationPrincipal User user, @PathVariable Long storyId, ModelMap model,
			HttpServletResponse response) throws IOException {
		model.put("user", user);
		Optional<Story> storyOpt = storyRepo.findById(storyId);

		if (storyOpt.isPresent()) {
			Story story = storyOpt.get();
			
			SortedSet<Comment> commentSet = commentRepo.findByStoryId(storyId);
	
			model.put("story", story);
			model.put("commentSet", commentSet);
			model.put("newComment", new Comment());
		} else {
			response.sendError(HttpStatus.NOT_FOUND.value(), "Oops, we couldn't find a story with that ID.");
			return "story";
		}
		return "story";
	}

	@GetMapping("story/{storyId}/editStory")
	public String editStory(@AuthenticationPrincipal User user, @PathVariable Long storyId,
			ModelMap model, HttpServletResponse response) throws IOException {
		
		Optional<Story> storyOpt = storyRepo.findById(storyId);
		Story story = new Story();
		
		if (storyOpt.isPresent()) {
			story = storyOpt.get();
			if (story.getUser().getId().equals(user.getId())) {
				model.put("user", user);
				model.put("story", story);
				return "editStory";
			} else {
				response.sendError(HttpStatus.NOT_FOUND.value(), "You don't have permission to access this.");
				return "error";
			}
		}
		
		
		return "editStory";
	}

	@PostMapping("story/{storyId}/editStory")
	public String saveChanges(@AuthenticationPrincipal User user, Story story, @PathVariable Long storyId,
			ModelMap model, HttpServletResponse response) throws IOException {

		Optional<Story> storyOpt = storyRepo.findById(storyId);
		Story oldStory = new Story();
		
		if (storyOpt.isPresent()) {
			oldStory = storyOpt.get();
		}
		
		model.put("story", story);
		Story newStory = new Story();
		
		newStory = storyService.edit(oldStory, story);
		storyRepo.save(newStory);

		return "redirect:/story/" + storyId;
	}
	
	@DeleteMapping("story/{storyId}")
	public void deleteStory(@PathVariable Long storyId, HttpServletResponse response) throws IOException {
		
		storyRepo.deleteById(storyId);

	}
}
