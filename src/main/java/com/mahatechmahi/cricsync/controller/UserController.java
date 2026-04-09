package com.mahatechmahi.cricsync.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahatechmahi.cricsync.entity.User;
import com.mahatechmahi.cricsync.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	// 1. API to create a new user (React will call this when someone registers)
	@PostMapping("/register")
	public User registerUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}

	// 2. API to get all users (Great for testing if data is actually saving)
	@GetMapping("/all")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}