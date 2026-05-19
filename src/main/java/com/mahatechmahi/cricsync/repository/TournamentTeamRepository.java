package com.mahatechmahi.cricsync.repository;

import com.mahatechmahi.cricsync.entity.TournamentTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TournamentTeamRepository extends JpaRepository<TournamentTeam, Long> {
    List<TournamentTeam> findByTournamentIdOrderByPointsDesc(Long tournamentId);
}
