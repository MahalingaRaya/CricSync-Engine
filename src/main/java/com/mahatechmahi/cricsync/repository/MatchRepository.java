package com.mahatechmahi.cricsync.repository;

import com.mahatechmahi.cricsync.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    
    // 1. Keeps your existing tournament relationship infrastructure working perfectly
    List<Match> findByTournamentId(Long tournamentId);

    // 2. Corrected: Finds the latest running match where status string equals "LIVE"
    Optional<Match> findFirstByStatusOrderByIdDesc(String status);
}
