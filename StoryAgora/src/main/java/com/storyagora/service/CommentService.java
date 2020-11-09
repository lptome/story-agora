package com.storyagora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storyagora.domain.Comment;
import com.storyagora.repositories.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepo;

	public Comment save(Comment comment) {

		return commentRepo.save(comment);
	}

	public Comment edit(Comment oldComment, Comment comment) {

		// Create new Comment object
		Comment newComment = new Comment();

		// Assign details to this new object.
		newComment.setId(oldComment.getId());
		newComment.setStory(oldComment.getStory());
		newComment.setUser(oldComment.getUser());
		newComment.setText(comment.getText());

		return newComment;

	}

}
