package com.mahatechmahi.cricsync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahatechmahi.cricsync.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
	// This will handle saving the "Apply" clicks!
}