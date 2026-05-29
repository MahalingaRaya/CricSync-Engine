package com.mahatechmahi.cricsync.repository;

import com.mahatechmahi.cricsync.entity.CommentaryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentaryLogRepository extends JpaRepository<CommentaryLog, Long> {
    List<CommentaryLog> findByMatchIdOrderByIdDesc(Long matchId);
}
