package com.storyagora.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class PointID implements Serializable{
	
	
	private static final long serialVersionUID = -3559035688770287412L;
	private User user;
	private Story story;
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	public Story getStory() {
		return story;
	}
	public void setStory(Story story) {
		this.story = story;
	}
	

}
