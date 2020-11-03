package com.storyagora.web;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.storyagora.domain.Story;
import com.storyagora.domain.User;
import com.storyagora.repositories.StoryRepository;
import com.storyagora.repositories.UserRepository;

@Controller
public class ProfileController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private StoryRepository storyRepo;

	@GetMapping("u/{username}")
	public String getUser(@PathVariable String username, ModelMap model, HttpServletResponse response)
			throws IOException {

		Optional<User> userOpt = Optional.ofNullable(userRepo.findByUsername(username));

		if (userOpt.isPresent()) {
			User user = userOpt.get();
			model.put("user", user);

			List<Story> stories = storyRepo.findByUser(user);
			model.put("stories", stories);

		} else {
			response.sendError(HttpStatus.NOT_FOUND.value(), "Oops, we couldn't find anyone with that username.");
			return "error";
		}
		return "profile";
	}
}
