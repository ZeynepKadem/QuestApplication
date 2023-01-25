package com.project.app.responses;

import java.util.List;
import com.project.app.entities.Post;

import lombok.Data;

@Data
public class PostResponse {
	
	private Long id;
	private Long userId;
	private String userName;
	private String title;
	private String text;
	private List<LikeResponse> postLikes;
	
	public PostResponse(Post entity, List<LikeResponse> likes) {
		this.id=entity.getId();
		this.userId=entity.getUser().getId();
		this.userName=entity.getUser().getUserName();
		this.text=entity.getText();
		this.postLikes=likes;
	}
	

}
