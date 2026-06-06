package com.mahatechmahi.cricsync.controller;

import com.mahatechmahi.cricsync.entity.TournamentTeam;
import com.mahatechmahi.cricsync.service.TournamentTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tournament-teams")
public class TournamentTeamController {

    @Autowired
    private TournamentTeamService tournamentTeamService;

    @PostMapping
    public TournamentTeam addTournamentTeam(@RequestBody TournamentTeam tournamentTeam) {
        return tournamentTeamService.saveTournamentTeam(tournamentTeam);
    }

    @GetMapping
    public List<TournamentTeam> getAllTournamentTeams() {
        return tournamentTeamService.getAllTournamentTeams();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TournamentTeam> getTournamentTeamById(@PathVariable Long id) {
        Optional<TournamentTeam> tournamentTeam = tournamentTeamService.getTournamentTeamById(id);
        return tournamentTeam.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
