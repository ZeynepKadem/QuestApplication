package com.project.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.app.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String username);

}
