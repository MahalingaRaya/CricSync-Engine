package com.mahatechmahi.cricsync.service;

import com.mahatechmahi.cricsync.entity.BallByBall;
import com.mahatechmahi.cricsync.entity.Match;
import com.mahatechmahi.cricsync.repository.BallByBallRepository;
import com.mahatechmahi.cricsync.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BallByBallService {

    @Autowired
    private BallByBallRepository ballByBallRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Transactional // Ensures the ball and the match score update together or fail together
    public BallByBall saveBall(BallByBall ball) {
        
        // 1. Find the match this ball belongs to
        if (ball.getMatch() != null && ball.getMatch().getId() != null) {
            Optional<Match> matchOpt = matchRepository.findById(ball.getMatch().getId());
            
            if (matchOpt.isPresent()) {
                Match match = matchOpt.get();
                
                // Calculate total runs for this specific delivery
                int runsScored = ball.getRunsScored() != null ? ball.getRunsScored() : 0;
                int extraRuns = ball.getExtraRuns() != null ? ball.getExtraRuns() : 0;
                int totalRunsOnBall = runsScored + extraRuns;
                
                boolean isWicket = ball.getIsWicket() != null ? ball.getIsWicket() : false;

                // 2. Add to Team A (Innings 1) or Team B (Innings 2)
                if (ball.getInningsNumber() != null && ball.getInningsNumber() == 1) {
                    match.setRunsA(match.getRunsA() + totalRunsOnBall);
                    match.setBallsA(match.getBallsA() + 1);
                    if (isWicket) {
                        match.setWicketsA(match.getWicketsA() + 1);
                    }
                } else if (ball.getInningsNumber() != null && ball.getInningsNumber() == 2) {
                    match.setRunsB(match.getRunsB() + totalRunsOnBall);
                    match.setBallsB(match.getBallsB() + 1);
                    if (isWicket) {
                        match.setWicketsB(match.getWicketsB() + 1);
                    }
                }
                
                // 3. Save the updated match score
                matchRepository.save(match);
            }
        }
        
        // 4. Finally, save the ball record itself
        return ballByBallRepository.save(ball);
    }

    public List<BallByBall> getAllBalls() {
        return ballByBallRepository.findAll();
    }

    public Optional<BallByBall> getBallById(Long id) {
        return ballByBallRepository.findById(id);
    }
}
