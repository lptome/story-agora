package com.storyagora.web;

import java.io.IOException;
import java.util.Optional;
import java.util.SortedSet;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.storyagora.domain.Comment;
import com.storyagora.domain.Story;
import com.storyagora.domain.User;
import com.storyagora.repositories.CommentRepository;
import com.storyagora.repositories.StoryRepository;

@Controller
@RequestMapping("/story/{storyId}/comments")
public class CommentController {

	@Autowired
	public CommentRepository commentRepo;
	@Autowired
	public StoryRepository storyRepo;

	@GetMapping("")
	@ResponseBody
	public SortedSet<Comment> getComments(@PathVariable Long storyId) {

		SortedSet<Comment> findByStoryId = commentRepo.findByStoryId(storyId);
		return findByStoryId;

	}

	@PostMapping("")
	public String postComment(@AuthenticationPrincipal User user, @PathVariable Long storyId, Comment comment) {

		Optional<Story> storyOpt = storyRepo.findById(storyId);
		if (storyOpt.isPresent()) {
			comment.setStory(storyOpt.get());
			comment.setUser(user);
			commentRepo.save(comment);
		}

		return "redirect:/story/" + storyId;
	}

	@DeleteMapping("/{commentId}")
	public void deleteComment(@PathVariable Long commentId, HttpServletResponse response) throws IOException {
		
		commentRepo.deleteById(commentId);
		
	}
}
