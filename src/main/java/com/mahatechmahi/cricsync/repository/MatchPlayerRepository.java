package com.mahatechmahi.cricsync.repository;

import com.mahatechmahi.cricsync.entity.MatchPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchPlayerRepository extends JpaRepository<MatchPlayer, Long> {
    // This allows us to instantly find all 11 players for a specific team in a specific match
    List<MatchPlayer> findByMatchIdAndTeamName(Long matchId, String teamName);
    
    // NEW: This allows React to fetch all 22 players for the match at once
    List<MatchPlayer> findByMatchId(Long matchId);
}
