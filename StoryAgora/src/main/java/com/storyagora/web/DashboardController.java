package com.storyagora.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.storyagora.domain.User;

@Controller
public class DashboardController {

	@GetMapping("/")
	public String rootView() {
		return "index";
	}

	@GetMapping("/dashboard")
	public String dashboard(@AuthenticationPrincipal User user, ModelMap model) {
		model.put("user", user);
		return "dashboard";
	}
}
