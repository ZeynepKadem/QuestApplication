package com.project.app.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.entities.User;
import com.project.app.responses.UserResponse;
import com.project.app.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService; //constructor injection yapmamız gerek

	
	public UserController(UserService userService) {
		
		this.userService = userService;
	}

	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
		
	}
	
	@PostMapping
	public User createUser(User newUser) {
		return userService.saveOneUser(newUser);
	}

	@GetMapping("/userId")
	public UserResponse getOneUser(Long userId) {
		return new UserResponse (userService.getOneUserById(userId));
		
		}
	@PutMapping("/userId")
	public User updateOneUser(Long userId,User newUser) {
	
		return userService.updateOneUser(userId, newUser);
	}
   
    @DeleteMapping("/userId")

    public void deleteOneUser(Long userId) {
	
		
		userService.deleteOneUser(userId);
	}
    
    @GetMapping("/activity/userId")
    public List<Object> getUserActivity(@PathVariable Long userId){
    	return userService.getUserActivity(userId);
    }
    
		
	
}

