package com.project.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.app.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByUserIdAndPostId(Long userId, Long postId);

	List<Comment> findByUserId(Long userId);

	List<Comment> findByPostId(Long postId);

	
	@Query(value= "select * from comment where post_id in : postIdList limit 5", nativeQuery=true)
	List<Comment> findUserCommentsByPostId(@Param("postIdList") List<Long> postIdList);
	
}
