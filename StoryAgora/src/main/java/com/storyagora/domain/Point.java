package com.storyagora.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Point {
	
	
	private PointID pk;
	private Boolean upvote;
	
	@EmbeddedId
	public PointID getPk() {
		return pk;
	}
	public void setPk(PointID pk) {
		this.pk = pk;
	}
	public Boolean getUpvote() {
		return upvote;
	}
	public void setUpvote(Boolean upvote) {
		this.upvote = upvote;
	}
	
	
}
