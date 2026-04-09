package com.mahatechmahi.cricsync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahatechmahi.cricsync.entity.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
	// This gives us save() and findAll() for matches automatically!
}