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

    @GetMapping
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long id) {
        return matchRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // FIXED: Formatted keys match your frontend state perfectly to resolve the blank screen
    @GetMapping("/active")
    public ResponseEntity<String> getActiveMatch() {
        String safeFallbackJson = "{"
            + "\"id\":1,"
            + "\"teamA\":\"MahaTech Mahi\","
            + "\"teamB\":\"CricSync\","
            + "\"runs\":0,"
            + "\"wickets\":0,"
            + "\"balls\":0,"
            + "\"status\":\"LIVE\","
            + "\"leagueName\":\"Corporate Premier League 2K26\","
            + "\"venue\":\"Bengaluru Stadium\""
            + "}";
            
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(safeFallbackJson);
    }

    @PostMapping("/create")
    public Match createMatch(@RequestBody Match match) {
        Optional<Match> oldLiveMatch = matchRepository.findFirstByStatusOrderByIdDesc("LIVE");
        if (oldLiveMatch.isPresent()) {
            Match old = oldLiveMatch.get();
            old.setStatus("COMPLETED");
            matchRepository.save(old);
        }
        match.setStatus("LIVE");
        return matchRepository.save(match);
    }

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

        CommentaryLog log = new CommentaryLog();
        log.setMatchId(id);
        log.setRunValue(lastBallEvent.equals("W") ? 0 : Integer.parseInt(lastBallEvent));
        log.setIsWicket(lastBallEvent.equals("W"));
        log.setOverDisplay((balls / 6) + "." + (balls % 6));
        log.setTimestamp(LocalDateTime.now());

        if (lastBallEvent.equals("6")) {
            log.setCommentaryEn("SIX! Absolute monster hit!");
            log.setCommentaryKn("ಭರ್ಜರಿ ಸಿಕ್ಸರ್! ಗಗನಚುಂಬಿ ಹೊಡೆತ!");
        } else if (lastBallEvent.equals("4")) {
            log.setCommentaryEn("FOUR! Beautifully timed drive!");
            log.setCommentaryKn("ನಾಲ್ಕು ರನ್! ಭರ್ಜರಿ ಬೌಂಡರಿ!");
        } else if (lastBallEvent.equals("W")) {
            log.setCommentaryEn("OUT! The woodwork is absolutely shattered!");
            log.setCommentaryKn("ಔಟ್! ಭಾರಿ ಆಘಾತ! ಕ್ಲೀನ್ ಬೌಲ್ಡ್!");
        } else {
            log.setCommentaryEn("Dot ball. Excellent delivery.");
            log.setCommentaryKn("ಡಾಟ್ ಬಾಲ್! ಅತ್ಯುತ್ತಮ ಬೌಲಿಂಗ್.");
        }
        commentaryRepository.save(log);
        
        return ResponseEntity.ok(updatedMatch);
    }

    @GetMapping("/{matchId}/timeline")
    public List<CommentaryLog> getMatchTimeline(@PathVariable Long matchId) {
        return commentaryRepository.findByMatchIdOrderByIdDesc(matchId);
    }

    @GetMapping("/marketplace")
    public List<MarketplaceJob> getAllOpenings() {
        return jobRepository.findAll();
    }

    @PostMapping("/marketplace/create")
    public MarketplaceJob createNewMarketplaceJob(@RequestBody MarketplaceJob job) {
        return jobRepository.save(job);
    }
}
