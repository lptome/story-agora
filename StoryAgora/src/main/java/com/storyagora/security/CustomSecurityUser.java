package com.storyagora.security;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

import com.storyagora.domain.User;

public class CustomSecurityUser extends User implements UserDetails{
	
	private static final long serialVersionUID = 5758478331488607008L;
	
	public CustomSecurityUser() { }
	
	public CustomSecurityUser(User user) {
		this.setAuthorities(user.getAuthorities());
		this.setId(user.getId());
		this.setName(user.getName());
		this.setBiography(user.getBiography());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
	}
	
	@Override
	public Set<Authority> getAuthorities() {
		return super.getAuthorities();
	}
	
	@Override
	public String getPassword() {
		return super.getPassword();
	}
	
	@Override
	public String getUsername() {
		return super.getUsername();
	}
	
	@Override
	public String getBiography() {
		return super.getBiography();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
