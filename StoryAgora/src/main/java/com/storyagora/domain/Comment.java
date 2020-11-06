package com.storyagora.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Comment {
	
	private CommentID pk;
	private String text;
	
	@EmbeddedId
	public CommentID getPk() {
		return pk;
	}
	public void setPk(CommentID pk) {
		this.pk = pk;
	}
	
	@Column(length=1000)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
