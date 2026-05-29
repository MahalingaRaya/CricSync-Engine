package com.mahatechmahi.cricsync.entity; // Updated to match your exact directory

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "commentary_logs")
public class CommentaryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long matchId;
    private Integer runValue;
    private Boolean isWicket;
    private String overDisplay;
    private String commentaryEn;
    private String commentaryKn;
    private LocalDateTime timestamp;

    // Explicit Getters and Setters to guarantee compilation without Lombok
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getMatchId() { return matchId; }
    public void setMatchId(Long matchId) { this.matchId = matchId; }

    public Integer getRunValue() { return runValue; }
    public void setRunValue(Integer runValue) { this.runValue = runValue; }

    public Boolean getIsWicket() { return isWicket; }
    public void setIsWicket(Boolean isWicket) { this.isWicket = isWicket; }

    public String getOverDisplay() { return overDisplay; }
    public void setOverDisplay(String overDisplay) { this.overDisplay = overDisplay; }

    public String getCommentaryEn() { return commentaryEn; }
    public void setCommentaryEn(String commentaryEn) { this.commentaryEn = commentaryEn; }

    public String getCommentaryKn() { return commentaryKn; }
    public void setCommentaryKn(String commentaryKn) { this.commentaryKn = commentaryKn; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
