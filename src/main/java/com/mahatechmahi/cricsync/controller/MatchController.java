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
@CrossOrigin(origins = "*") // Safely links Vercel and local environments
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

    // 3. Fetch the current running live game matching your frontend polling layout
    @GetMapping("/active")
    public ResponseEntity<Match> getActiveMatch() {
        return matchRepository.findFirstByStatusOrderByIdDesc("LIVE")
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. Broadcast a match from the Organizer Form, marking old games COMPLETED
    @PostMapping("/create")
    public Match createMatch(@RequestBody Match match) {
        // Automatically find and mark any old running matches as "COMPLETED"
        matchRepository.findFirstByStatusOrderByIdDesc("LIVE").ifPresent(oldMatch -> {
            oldMatch.setStatus("COMPLETED");
            matchRepository.save(oldMatch);
        });
        
        // Ensure the brand new match starts fresh and marked as "LIVE"
        match.setStatus("LIVE");
        if (match.getRunsA() == null) match.setRunsA(0);
        if (match.getWicketsA() == null) match.setWicketsA(0);
        if (match.getBallsA() == null) match.setBallsA(0);
        
        return matchRepository.save(match);
    }

    // 5. ECOSYSTEM UPGRADE: Update score live AND automatically compile bilingual timeline entries
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

    // 6. ECOSYSTEM UPGRADE: Fetch full ball history logs array for a match
    @GetMapping("/{matchId}/timeline")
    public List<CommentaryLog> getMatchTimeline(@PathVariable Long matchId) {
        return commentaryRepository.findByMatchIdOrderByIdDesc(matchId);
    }

    // 7. ECOSYSTEM UPGRADE: Fetch dynamic marketplace listings
    @GetMapping("/marketplace")
    public List<MarketplaceJob> getAllOpenings() {
        return jobRepository.findAll();
    }

    // 8. ECOSYSTEM UPGRADE: Post a new job directly from the client application context
    @PostMapping("/marketplace/create")
    public MarketplaceJob createNewMarketplaceJob(@RequestBody MarketplaceJob job) {
        return jobRepository.save(job);
    }
}
