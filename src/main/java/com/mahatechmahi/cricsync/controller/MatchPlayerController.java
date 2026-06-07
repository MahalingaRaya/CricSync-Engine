package com.mahatechmahi.cricsync.controller;

import com.mahatechmahi.cricsync.entity.MatchPlayer;
import com.mahatechmahi.cricsync.repository.MatchPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-players")
@CrossOrigin(origins = "*") // Allows Vercel to talk to Render
public class MatchPlayerController {

    @Autowired
    private MatchPlayerRepository repository;

    // This endpoint catches all 22 players at once and saves them instantly
    @PostMapping("/bulk")
    public ResponseEntity<List<MatchPlayer>> savePlayers(@RequestBody List<MatchPlayer> players) {
        List<MatchPlayer> savedPlayers = repository.saveAll(players);
        return ResponseEntity.ok(savedPlayers);
    }

    // NEW: Fetches all 22 players for a specific match so React can show them in dropdowns
    @GetMapping("/match/{matchId}")
    public ResponseEntity<List<MatchPlayer>> getPlayersByMatch(@PathVariable Long matchId) {
        return ResponseEntity.ok(repository.findByMatchId(matchId));
    }
}
