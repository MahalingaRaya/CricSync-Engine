package com.mahatechmahi.cricsync.service;

import com.mahatechmahi.cricsync.entity.TournamentTeam;
import com.mahatechmahi.cricsync.repository.TournamentTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentTeamService {

    @Autowired
    private TournamentTeamRepository tournamentTeamRepository;

    public TournamentTeam saveTournamentTeam(TournamentTeam tournamentTeam) {
        return tournamentTeamRepository.save(tournamentTeam);
    }

    public List<TournamentTeam> getAllTournamentTeams() {
        return tournamentTeamRepository.findAll();
    }

    public Optional<TournamentTeam> getTournamentTeamById(Long id) {
        return tournamentTeamRepository.findById(id);
    }
}
