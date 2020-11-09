package com.storyagora.web;

import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.storyagora.domain.Story;
import com.storyagora.domain.User;
import com.storyagora.repositories.StoryRepository;

@Controller
public class DashboardController {

	@Autowired
	private StoryRepository storyRepo;

	@GetMapping("/")
	public String rootView(@AuthenticationPrincipal User user) {

		if (user != null) {
			return "redirect:/dashboard";
		}

		return "index";
	}

	@GetMapping("/dashboard")
	public String dashboard(@AuthenticationPrincipal User user, ModelMap model) {

		SortedSet<Story> recentStories = storyRepo.findRecent();
		model.put("stories", recentStories);
		model.put("user", user);
		return "dashboard";
	}
}
