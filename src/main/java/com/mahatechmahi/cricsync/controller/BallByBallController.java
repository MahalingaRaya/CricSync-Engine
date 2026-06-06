package com.mahatechmahi.cricsync.controller;

import com.mahatechmahi.cricsync.entity.BallByBall;
import com.mahatechmahi.cricsync.service.BallByBallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ball-by-ball")
public class BallByBallController {

    @Autowired
    private BallByBallService ballByBallService;

    @PostMapping
    public BallByBall recordBall(@RequestBody BallByBall ball) {
        return ballByBallService.saveBall(ball);
    }

    @GetMapping
    public List<BallByBall> getAllBalls() {
        return ballByBallService.getAllBalls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BallByBall> getBallById(@PathVariable Long id) {
        Optional<BallByBall> ball = ballByBallService.getBallById(id);
        return ball.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
