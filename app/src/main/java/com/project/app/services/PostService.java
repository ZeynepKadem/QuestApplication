package com.project.app.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.project.app.entities.Post;
import com.project.app.entities.User;
import com.project.app.repository.PostRepository;
import com.project.app.requests.PostCreateRequest;
import com.project.app.requests.PostUpdateRequest;
import com.project.app.responses.LikeResponse;
import com.project.app.responses.PostResponse;

@Service
public class PostService {
	private PostRepository postRepository;
	private UserService userService;
	private LikeService likeService;

	public PostService(PostRepository postRepository,UserService userService) {
		
		this.postRepository = postRepository;
		this.userService=userService;
		
	}

	//birbirini çağırıyor ve sonsuza giriyor bu yüzden setter ile çağırdım.
	public void setLikeService(LikeService likeService) {
		this.likeService = likeService;
	}

	public List<PostResponse> getAllPosts(Optional<Long> userId) {
	
		List<Post> postList;
		if(userId.isPresent()) {
			postList= postRepository.findByUserId(userId.get());
		}else {
			postList= postRepository.findAll();
		}
			return postList.stream().map(p -> {
				List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(p.getId()));
				return new PostResponse(p,likes);}).collect(Collectors.toList());
	}

	public Post getOnePostById(Long postId) {
		return postRepository.findById(postId).orElse(null);
	}
	
	public PostResponse getOnePostByIdWithLikes(Long postId) {
		Post post= postRepository.findById(postId).orElse(null);
		List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null),Optional.of(postId));
		return new PostResponse(post,likes);
	}

	public Post createOnePost(PostCreateRequest postCreateRequest) {
	// once bakıyorum o posta ait userId var mı? Var. getOneUser methodu!
		User user = userService.getOneUserById(postCreateRequest.getUserId());
		if(user==null) {
			return null;
		}
		Post toSave = new Post();
		toSave.setId(postCreateRequest.getId());
		toSave.setText(postCreateRequest.getText());
		toSave.setTitle(postCreateRequest.getTitle());
		toSave.setUser(user);
		toSave.setCreateDate(new Date());
		return postRepository.save(toSave);
	}

	public Post updateOnePostById(Long postId, PostUpdateRequest postUpdateRequest) {
		Optional<Post> post = postRepository.findById(postId);
		if (post.isPresent()) {
			Post toUpdate = post.get();
			toUpdate.setText(postUpdateRequest.getText());
			toUpdate.setTitle(postUpdateRequest.getTitle());
			postRepository.save(toUpdate);
			
			return toUpdate;
		}return null;
			}

	public void deleteOnePostById(Long postId) {
		postRepository.deleteById(postId);
	}


}
