package com.storyagora.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.storyagora.domain.User;
import com.storyagora.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login(@AuthenticationPrincipal User user) {
		
		if (user != null) {
			return "redirect:/dashboard";			
		}
		
		return "login";
	}

	@GetMapping("/register")
	public String register(ModelMap model) {
		model.put("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerPost(@ModelAttribute User user, ModelMap model) {
		
		if (!userService.userAlreadyExists(user)) {			
			userService.save(user);
			return "redirect:/login";
		} else {
			model.put("errorMessage", "Sorry, that username is taken.");
			return "/register";
		}
	}
}
