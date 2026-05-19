package com.mahatechmahi.cricsync.repository;

import com.mahatechmahi.cricsync.entity.BallByBall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BallByBallRepository extends JpaRepository<BallByBall, Long> {
    List<BallByBall> findByMatchIdOrderByInningsNumberAscOverNumberAscBallNumberAsc(Long matchId);
}
