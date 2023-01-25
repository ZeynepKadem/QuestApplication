package com.project.app.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.app.entities.Like;
import com.project.app.entities.Post;
import com.project.app.entities.User;
import com.project.app.repository.LikeRepository;
import com.project.app.requests.LikeCreateRequest;
import com.project.app.responses.LikeResponse;

@Service
public class LikeService {
	private LikeRepository likeRepository;
	private UserService userService;
	private PostService postService;
	

	public LikeService(LikeRepository likeRepository,UserService userService,PostService postService) {
		super();
		this.likeRepository = likeRepository;
		this.userService=userService;
		this.postService=postService;
	}


	public List<LikeResponse> getAllLikesWithParam(Optional <Long> userId, Optional<Long> postId) {
		List<Like> likeList;
		if(userId.isPresent() && postId.isPresent()) {
			likeList = likeRepository.findByUserIdAndPostId(userId.get() , postId.get());
		}else if (userId.isPresent()) {
			likeList= likeRepository.findByUserId(userId.get());
			
		}else if(postId.isPresent()){
			likeList=likeRepository.findByPostId(postId.get());	
		}else
			likeList=likeRepository.findAll();
		
		return likeList.stream().map(l->new LikeResponse(l)).collect(Collectors.toList());
	}


	public Like getOneLikeById(Long likeId) {
		return likeRepository.findById(likeId).orElse(null);
	}


	public Like createOneLike(LikeCreateRequest createRequest) {
		
		User user = userService.getOneUserById(createRequest.getUserId());
		Post post = postService.getOnePostById(createRequest.getPostId());
		if(user != null && post != null) {
			Like likeToSave = new Like();
			likeToSave.setId(createRequest.getId());
			likeToSave.setPost(post);
			likeToSave.setUser(user);
			
			return likeRepository.save(likeToSave);
		}else
		return null;
	}


	public void deleteOneLikeById(Long likeId) {
		likeRepository.deleteById(likeId);
		
	}



}
