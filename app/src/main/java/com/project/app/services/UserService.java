package com.project.app.services;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.app.entities.Comment;
import com.project.app.entities.Like;
import com.project.app.entities.User;
import com.project.app.repository.CommentRepository;
import com.project.app.repository.LikeRepository;
import com.project.app.repository.PostRepository;
import com.project.app.repository.UserRepository;

@Service

public class UserService {
	
	UserRepository userRepository;
	LikeRepository likeRepository;
	CommentRepository commentRepository;
	PostRepository postRepository;


	public UserService(UserRepository userRepository, LikeRepository likeRepository,
			CommentRepository commentRepository, PostRepository postRepository) {
		super();
		this.userRepository = userRepository;
		this.likeRepository = likeRepository;
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	public User saveOneUser(User newUser) {
		
		return userRepository.save(newUser);
	}

	public User getOneUserById(Long userId) {
		
		return userRepository.findById(userId).orElse(null);

}

	public User updateOneUser(Long userId, User newUser) {
	
		Optional <User> user =userRepository.findById(userId);
		if(user.isPresent()) {
			
			User foundUser =user.get();
			foundUser.setUserName(newUser.getUserName());
			foundUser.setPassword(newUser.getPassword());
			foundUser.setAvatar(newUser.getAvatar());
			userRepository.save(foundUser);
			return foundUser;
		}else {
			return null;
		}
		
	}

	public void deleteOneUser(Long userId) {
		userRepository.deleteById(userId);
		
	}

	public User getOneUserByUserName(String userName) {
		
		return userRepository.findByUserName(userName);
	}

	public List<Object> getUserActivity(Long userId) {
		List<Long> postIdList = postRepository.findTopByUserId(userId);
		if (postIdList.isEmpty()) 
			return null;
			List<Comment> comments =commentRepository.findUserCommentsByPostId(postIdList);
			List<Like> likes = likeRepository.findUserLikesByPostId(postIdList);
			List<Object> result = new ArrayList<>();
			result.addAll(comments);
			result.addAll(likes);
			return result;
		
		
	}
}