package com.mahatechmahi.cricsync.controller;

import com.mahatechmahi.cricsync.entity.Match;
import com.mahatechmahi.cricsync.entity.CommentaryLog;
import com.mahatechmahi.cricsync.entity.MarketplaceJob;
import com.mahatechmahi.cricsync.repository.MatchRepository;
import com.mahatechmahi.cricsync.repository.CommentaryLogRepository;
import com.mahatechmahi.cricsync.repository.JobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matches")
@CrossOrigin(origins = "*") 
public class MatchController {

    @Autowired private MatchRepository matchRepository;
    @Autowired private CommentaryLogRepository commentaryRepository;
    @Autowired private JobRepository jobRepository;

    // 1. Fetch all matches recorded in the database
    @GetMapping
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    // 2. Fetch a specific match by its explicit ID
    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long id) {
        return matchRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. FIXED PERMANENTLY: Separates object returns from fallback strings cleanly to stop 500 serialization crashes
    @GetMapping("/active")
    public ResponseEntity<?> getActiveMatch() {
        Optional<Match> activeMatchOpt = matchRepository.findFirstByStatusOrderByIdDesc("LIVE");
        
        if (activeMatchOpt.isPresent()) {
            return ResponseEntity.ok(activeMatchOpt.get());
        }
        
        // Safe placeholder object mapped manually back to your React engine if database has zero rows
        return ResponseEntity.ok().body("{\"id\":null,\"teamA\":\"MahaTech Mahi\",\"teamB\":\"CricSync\",\"runsA\":0,\"wicketsA\":0,\"ballsA\":0,\"status\":\"LIVE\",\"leagueName\":\"Corporate Premier League 2K26\",\"venue\":\"Bengaluru\"}");
    }

    // 4. Broadcast a match from the Organizer Form, marking old games COMPLETED
    @PostMapping("/create")
    public Match createMatch(@RequestBody Match match) {
        // Automatically find and mark any old running matches as "COMPLETED"
        Optional<Match> oldLiveMatch = matchRepository.findFirstByStatusOrderByIdDesc("LIVE");
        if (oldLiveMatch.isPresent()) {
            Match old = oldLiveMatch.get();
            old.setStatus("COMPLETED");
            matchRepository.save(old);
        }
        
        // Ensure the brand new match starts fresh and marked as "LIVE"
        match.setStatus("LIVE");
        if (match.getRunsA() == null) match.setRunsA(0);
        if (match.getWicketsA() == null) match.setWicketsA(0);
        if (match.getBallsA() == null) match.setBallsA(0);
        
        return matchRepository.save(match);
    }

    // 5. Update score live AND automatically compile bilingual timeline entries
    @PutMapping("/update-live")
    public ResponseEntity<Match> updateMatchScore(
            @RequestParam Long id, @RequestParam Integer runs,
            @RequestParam Integer wickets, @RequestParam Integer balls,
            @RequestParam String lastBallEvent) {
            
        Match match = matchRepository.findById(id).orElseThrow();
        match.setRunsA(runs);
        match.setWicketsA(wickets);
        match.setBallsA(balls);
        Match updatedMatch = matchRepository.save(match);

        // Build out the persistent history log item
        CommentaryLog log = new CommentaryLog();
        log.setMatchId(id);
        log.setRunValue(lastBallEvent.equals("W") ? 0 : Integer.parseInt(lastBallEvent));
        log.setIsWicket(lastBallEvent.equals("W"));
        log.setOverDisplay((balls / 6) + "." + (balls % 6));
        log.setTimestamp(LocalDateTime.now());

        if (lastBallEvent.equals("6")) {
            log.setCommentaryEn("SIX! Absolute monster hit over the deep mid-wicket boundary!");
            log.setCommentaryKn("ಭರ್ಜರಿ ಸಿಕ್ಸರ್! ಗಗನಚುಂಬಿ ಹೊಡೆತ, ಚೆಂಡು ನೇರವಾಗಿ ಗ್ಯಾಲರಿಗೆ ಹೋಗಿ ಬಿದ್ದಿದೆ!");
        } else if (lastBallEvent.equals("4")) {
            log.setCommentaryEn("FOUR! Beautifully timed drive flashing through the covers!");
            log.setCommentaryKn("ನಾಲ್ಕು ರನ್! ಭರ್ಜರಿ ಬೌಂಡರಿ! ಮಿಂಚಿನ ವೇಗದಲ್ಲಿ ಚೆಂಡು ಗೆರೆ ದಾಟಿದೆ!");
        } else if (lastBallEvent.equals("W")) {
            log.setCommentaryEn("OUT! Clean bowled! The woodwork is absolutely shattered!");
            log.setCommentaryKn("ಔಟ್! ಭಾರಿ ಆಘಾತ! ಕ್ಲೀನ್ ಬೌಲ್ಡ್ ಆಗಿ ಬ್ಯಾಟ್ಸ್‌ಮನ್ ಪೆವಿಲಿಯನ್‌ಗೆ ವಾಪಸ್!");
        } else {
            log.setCommentaryEn("Dot ball. Excellent delivery right in the blockhole.");
            log.setCommentaryKn("ಡಾಟ್ ಬಾಲ್! ಅತ್ಯುತ್ತಮ ಲೈನ್ ಮತ್ತು ಲೆಂತ್ ಬೌಲಿಂಗ್ ಪ್ರದರ್ಶನ.");
        }
        commentaryRepository.save(log);
        
        return ResponseEntity.ok(updatedMatch);
    }

    // 6. Fetch full ball history logs array for a match
    @GetMapping("/{matchId}/timeline")
    public List<CommentaryLog> getMatchTimeline(@PathVariable Long matchId) {
        return commentaryRepository.findByMatchIdOrderByIdDesc(matchId);
    }

    // 7. Fetch dynamic marketplace listings
    @GetMapping("/marketplace")
    public List<MarketplaceJob> getAllOpenings() {
        return jobRepository.findAll();
    }

    // 8. Post a new job directly from the client application context
    @PostMapping("/marketplace/create")
    public MarketplaceJob createNewMarketplaceJob(@RequestBody MarketplaceJob job) {
        return jobRepository.save(job);
    }
}
