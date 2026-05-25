package com.mahatechmahi.cricsync.repository;

import com.mahatechmahi.cricsync.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    
    // 1. Keeps your existing tournament infrastructure working perfectly
    List<Match> findByTournamentId(Long tournamentId);

    // 2. Added to handle the live real-time scoring data stream
    Optional<Match> findFirstByActiveTrueOrderByIdDesc();
}
