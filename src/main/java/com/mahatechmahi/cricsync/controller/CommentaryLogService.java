package com.mahatechmahi.cricsync.controller;

import com.mahatechmahi.cricsync.entity.CommentaryLog;
import com.mahatechmahi.cricsync.service.CommentaryLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commentary")
public class CommentaryLogController {

    @Autowired
    private CommentaryLogService commentaryLogService;

    @PostMapping
    public CommentaryLog addCommentary(@RequestBody CommentaryLog commentaryLog) {
        return commentaryLogService.saveCommentary(commentaryLog);
    }

    @GetMapping
    public List<CommentaryLog> getAllCommentary() {
        return commentaryLogService.getAllCommentary();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentaryLog> getCommentaryById(@PathVariable Long id) {
        Optional<CommentaryLog> commentary = commentaryLogService.getCommentaryById(id);
        return commentary.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
