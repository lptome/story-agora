package com.storyagora.web;

import java.io.IOException;
import java.util.Optional;
import java.util.SortedSet;

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
import com.storyagora.repositories.UserRepository;
import com.storyagora.service.UserService;

@Controller
public class ProfileController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private StoryRepository storyRepo;
	@Autowired
	private UserService userService;

	@GetMapping("u/{username}")
	public String getUser(@AuthenticationPrincipal User user, @PathVariable String username, ModelMap model,
			HttpServletResponse response) throws IOException {

		model.put("user", user);

		Optional<User> userOpt = Optional.ofNullable(userRepo.findByUsernameIgnoreCase(username));

		if (userOpt.isPresent()) {
			User profile = userOpt.get();
			model.put("profile", profile);
			SortedSet<Story> stories = storyRepo.findByUser(profile);
			model.put("stories", stories);

		} else {
			response.sendError(HttpStatus.NOT_FOUND.value(), "Oops, we couldn't find anyone with that username.");
			return "error";
		}
		return "profile";
	}

	@GetMapping("u/{username}/edit_profile")
	public String editProfile(@AuthenticationPrincipal User user, @PathVariable String username, ModelMap model,
			HttpServletResponse response) throws IOException {
		
		if (user.getUsername().equals(username)) {
			model.put("user", user);
			return "editProfile";
		} else {
			response.sendError(HttpStatus.FORBIDDEN.value(), "You don't have permission to access this page.");
			return "error";
		}

	}

	@PostMapping("u/{username}/edit_profile")
	public String saveChanges(@AuthenticationPrincipal User oldUser, User user, @PathVariable String username, HttpServletResponse response)
			throws IOException {
		
		//Calls User Service to update the User details.
		User newUser = userService.update(oldUser, user);
		userRepo.save(newUser);
		
	
		
		return "redirect:/u/" + username;
	}
}
