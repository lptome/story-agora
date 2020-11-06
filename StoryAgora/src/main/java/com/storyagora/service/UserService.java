package com.storyagora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import com.storyagora.domain.User;
import com.storyagora.repositories.UserRepository;
import com.storyagora.security.Authority;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder encoder;

	public User save(User user) {
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		Authority authority = new Authority();
		authority.setAuthority("ROLE_USER");
		authority.setUser(user);
		user.getAuthorities().add(authority);
		return userRepo.save(user);
	}

	public User update(User oldUser, User user) {

		// Create new User object
		User newUser = new User();

		// Assign details to this new object.
		// Static details (username, password) are pulled from the old user
		// and the updated details are obtained from the new one.
		newUser.setUsername(oldUser.getUsername());
		newUser.setAuthorities(oldUser.getAuthorities());
		newUser.setBiography(user.getBiography());
		newUser.setId(oldUser.getId());
		newUser.setName(user.getName());
		newUser.setPassword(oldUser.getPassword());
		newUser.setStories(oldUser.getStories());

		// Updates the Authentication Principal so that changes are
		// reflected without needing to end the current session
		Authentication authentication = new PreAuthenticatedAuthenticationToken(newUser, oldUser.getPassword(),
				oldUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return newUser;
	}

}
