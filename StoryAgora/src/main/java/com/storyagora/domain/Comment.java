package com.storyagora.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Comment extends Auditable<String> implements Comparable<Comment>{

	private Long id;
	private String text;
	private User user;
	private Story story;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(length = 1000)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
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
	
	@Override
	public int compareTo(Comment c) {
		int compared = this.getCreatedDate().compareTo(c.getCreatedDate());
		if (compared == 0) {
			compared = this.getId().compareTo(c.getId());
			return compared;
		} else return -compared;
	}

}
