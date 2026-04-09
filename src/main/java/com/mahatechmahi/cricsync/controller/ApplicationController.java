package com.mahatechmahi.cricsync.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mahatechmahi.cricsync.entity.Application;
import com.mahatechmahi.cricsync.repository.ApplicationRepository;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

	@Autowired
	private ApplicationRepository applicationRepository;

	@PostMapping("/apply")
	public Application applyForMatch(@RequestBody Application application) {
		return applicationRepository.save(application);
	}

	// API for the Organizer to Accept or Reject an application
	@PutMapping("/{id}/status")
	public Application updateApplicationStatus(@PathVariable Integer id, @RequestParam String status) {
		Application app = applicationRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Application not found"));

		app.setStatus(status);
		return applicationRepository.save(app);
	}

	@GetMapping("/all")
	public List<Application> getAllApplications() {
		return applicationRepository.findAll();
	}

}