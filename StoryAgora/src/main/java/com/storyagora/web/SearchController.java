package com.storyagora.web;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.storyagora.domain.Story;
import com.storyagora.domain.User;
import com.storyagora.repositories.StoryRepository;
import com.storyagora.repositories.UserRepository;

@Controller
public class SearchController {

	@Autowired
	private StoryRepository storyRepo;
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/search")
	public String search(@AuthenticationPrincipal User user, ModelMap model) {
		model.put("user", user);
		return "search";
	}

	@GetMapping("/results")
	public String searchResults(@AuthenticationPrincipal User user,
			@RequestParam(defaultValue = "") String search_query, ModelMap model, HttpServletResponse response)
			throws IOException {
		
		model.put("user", user);
		
		Optional<List<Story>> storyOpt = Optional.ofNullable(storyRepo.findByKeyword(search_query));
		Optional<List<User>> userOpt = Optional.ofNullable(userRepo.findByKeyword(search_query));
		
		if (storyOpt.isPresent()) {
			List<Story> stories = storyOpt.get();
			model.put("stories", stories);
		} 
		if (userOpt.isPresent()) {
			List<User> users = userOpt.get();
			model.put("users", users);
		}
		else {
			response.sendError(HttpStatus.NOT_FOUND.value(), "Oops, we couldn't find a story with that ID.");
			return "results";
		}

		return "results";
	}
}
