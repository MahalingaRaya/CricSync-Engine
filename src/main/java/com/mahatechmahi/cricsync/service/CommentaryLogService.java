package com.mahatechmahi.cricsync.service;

import com.mahatechmahi.cricsync.entity.CommentaryLog;
import com.mahatechmahi.cricsync.repository.CommentaryLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentaryLogService {

    @Autowired
    private CommentaryLogRepository commentaryLogRepository;

    public CommentaryLog saveCommentary(CommentaryLog commentaryLog) {
        return commentaryLogRepository.save(commentaryLog);
    }

    public List<CommentaryLog> getAllCommentary() {
        return commentaryLogRepository.findAll();
    }

    public Optional<CommentaryLog> getCommentaryById(Long id) {
        return commentaryLogRepository.findById(id);
    }
}
