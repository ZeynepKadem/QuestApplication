package com.project.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.app.entities.Comment;
import com.project.app.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

	List<Like> findByUserIdAndPostId(Long userId, Long postId);

	List<Like> findByUserId(Long userId);

	List<Like> findByPostId(Long postId);

	@Query(value= "select * from p_like where post_id in : postIdList limit 5", nativeQuery=true)
	List<Like> findUserLikesByPostId(@Param("postIdList") List<Long> postIdList);
}
