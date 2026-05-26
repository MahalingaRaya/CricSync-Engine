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

    // 3. Updated endpoint: Fetch the current running live game based on the "LIVE" status string
    @GetMapping("/active")
    public ResponseEntity<Match> getActiveMatch() {
        return matchRepository.findFirstByStatusOrderByIdDesc("LIVE")
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. Upgraded endpoint: Broadcast a match from the Organizer Form and set status to "LIVE"
    @PostMapping("/create")
    public Match createMatch(@RequestBody Match match) {
        // Automatically find and mark any old running matches as "COMPLETED"
        matchRepository.findFirstByStatusOrderByIdDesc("LIVE").ifPresent(oldMatch -> {
            oldMatch.setStatus("COMPLETED");
            matchRepository.save(oldMatch);
        });
        
        // Ensure the brand new match starts fresh and marked as "LIVE"
        match.setStatus("LIVE");
        return matchRepository.save(match);
    }

    // 5. Updated endpoint: Dynamically update scores live using your explicit Team A fields
    @PutMapping("/update")
    public ResponseEntity<Match> updateScore(@RequestParam int runs, @RequestParam int wickets, @RequestParam int balls) {
        return matchRepository.findFirstByStatusOrderByIdDesc("LIVE")
                .map(match -> {
                    match.setRunsA(runs);
                    match.setWicketsA(wickets);
                    match.setBallsA(balls);
                    Match updated = matchRepository.save(match);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
