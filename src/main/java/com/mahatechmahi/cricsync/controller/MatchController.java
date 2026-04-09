package com.mahatechmahi.cricsync.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahatechmahi.cricsync.entity.Match;
import com.mahatechmahi.cricsync.repository.MatchRepository;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

	@Autowired
	private MatchRepository matchRepository;

	// API to Post a Match
	@PostMapping("/post")
	public Match postMatch(@RequestBody Match match) {
		return matchRepository.save(match);
	}

	// API to see all available matches
	@GetMapping("/all")
	public List<Match> getAllMatches() {
		return matchRepository.findAll();
	}
}