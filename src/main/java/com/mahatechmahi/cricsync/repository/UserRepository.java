package com.mahatechmahi.cricsync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahatechmahi.cricsync.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	// Spring Boot automatically gives us save(), findAll(), findById(), etc.
	// We can add custom searches here later, like finding a user by their email!
	User findByEmail(String email);

}