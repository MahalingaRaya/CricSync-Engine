package com.mahatechmahi.cricsync.controller;

import com.mahatechmahi.cricsync.entity.Match;
import com.mahatechmahi.cricsync.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@CrossOrigin(origins = "*") // Crucial for connecting your React Vercel app safely
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    // 1. Existing endpoint: Fetch all matches recorded in the database
    @GetMapping
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    // 2. Existing endpoint: Fetch a specific match by its explicit ID
    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long id) {
        return matchRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. New endpoint: Fetch the current running live game for the React Dashboard home feed
    @GetMapping("/active")
    public ResponseEntity<Match> getActiveMatch() {
        return matchRepository.findFirstByActiveTrueOrderByIdDesc()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. Upgraded endpoint: Broadcast a match from the Organizer Form and make it the active game
    @PostMapping("/create")
    public Match createMatch(@RequestBody Match match) {
        // Automatically find and mark any old running matches as inactive
        matchRepository.findFirstByActiveTrueOrderByIdDesc().ifPresent(oldMatch -> {
            oldMatch.setActive(false);
            matchRepository.save(oldMatch);
        });
        
        // Ensure the brand new match starts fresh and marked as active
        match.setActive(true);
        return matchRepository.save(match);
    }

    // 5. New endpoint: Dynamically update scores live from the Scorer keypad buttons
    @PutMapping("/update")
    public ResponseEntity<Match> updateScore(@RequestParam int runs, @RequestParam int wickets, @RequestParam int balls) {
        return matchRepository.findFirstByActiveTrueOrderByIdDesc()
                .map(match -> {
                    match.setRuns(runs);
                    match.setWickets(wickets);
                    match.setBalls(balls);
                    Match updated = matchRepository.save(match);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
