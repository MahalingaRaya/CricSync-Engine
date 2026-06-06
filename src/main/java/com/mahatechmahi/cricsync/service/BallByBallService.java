package com.mahatechmahi.cricsync.service;

import com.mahatechmahi.cricsync.entity.BallByBall;
import com.mahatechmahi.cricsync.repository.BallByBallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BallByBallService {

    @Autowired
    private BallByBallRepository ballByBallRepository;

    public BallByBall saveBall(BallByBall ball) {
        return ballByBallRepository.save(ball);
    }

    public List<BallByBall> getAllBalls() {
        return ballByBallRepository.findAll();
    }

    public Optional<BallByBall> getBallById(Long id) {
        return ballByBallRepository.findById(id);
    }
}
