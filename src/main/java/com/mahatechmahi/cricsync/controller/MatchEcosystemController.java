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

@RestController
@RequestMapping("/api/matches")
@CrossOrigin(origins = "*")
public class MatchEcosystemController {

    @Autowired private MatchRepository matchRepository;
    @Autowired private CommentaryLogRepository commentaryRepository;
    @Autowired private JobRepository jobRepository;

    // 1. FIXED: Added the POST endpoint to handle initial match publishing on the base path
    @PostMapping
    public Match createMatch(@RequestBody Match match) {
        // Enforce basic initializations if missing from payload
        if (match.getRunsA() == null) match.setRunsA(0);
        if (match.getWicketsA() == null) match.setWicketsA(0);
        if (match.getBallsA() == null) match.setBallsA(0);
        return matchRepository.save(match);
    }

    // 2. FIXED: Added the active match retriever endpoint requested by your React polling loop
    @GetMapping("/active")
    public ResponseEntity<Match> getActiveMatch() {
        List<Match> matches = matchRepository.findAll();
        if (matches.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // Returns the latest active live record entry cleanly
        return ResponseEntity.ok(matches.get(matches.size() - 1));
    }

    // 3. FIXED: Added the explicit endpoint to process dynamic job posting requests
    @PostMapping("/marketplace/create")
    public MarketplaceJob createNewMarketplaceJob(@RequestBody MarketplaceJob job) {
        return jobRepository.save(job);
    }

    @PutMapping("/update-live")
    public ResponseEntity<?> updateMatchScore(
            @RequestParam Long id, @RequestParam Integer runs,
            @RequestParam Integer wickets, @RequestParam Integer balls,
            @RequestParam String lastBallEvent) {
            
        Match match = matchRepository.findById(id).orElseThrow();
        match.setRunsA(runs);
        match.setWicketsA(wickets);
        match.setBallsA(balls);
        matchRepository.save(match);

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
        return ResponseEntity.ok(match);
    }

    @GetMapping("/{matchId}/timeline")
    public List<CommentaryLog> getMatchTimeline(@PathVariable Long matchId) {
        return commentaryRepository.findByMatchIdOrderByIdDesc(matchId);
    }

    @GetMapping("/marketplace")
    public List<MarketplaceJob> getAllOpenings() {
        return jobRepository.findAll();
    }
}
