package com.mahatechmahi.cricsync.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ball_by_ball")
public class BallByBall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    @Column(name = "innings_number", nullable = false)
    private Integer inningsNumber;

    @Column(name = "over_number", nullable = false)
    private Integer overNumber;

    @Column(name = "ball_number", nullable = false)
    private Integer ballNumber;

    @Column(name = "runs_scored", nullable = false)
    private Integer runsScored;

    @Column(name = "extra_runs")
    private Integer extraRuns = 0;

    @Column(name = "is_wicket")
    private Boolean isWicket = false;

    @Column(name = "commentary_text", columnDefinition = "TEXT")
    private String commentaryText;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }
    public Integer getInningsNumber() { return inningsNumber; }
    public void setInningsNumber(Integer inningsNumber) { this.inningsNumber = inningsNumber; }
    public Integer getOverNumber() { return overNumber; }
    public void setOverNumber(Integer overNumber) { this.overNumber = overNumber; }
    public Integer getBallNumber() { return ballNumber; }
    public void setBallNumber(Integer ballNumber) { this.ballNumber = ballNumber; }
    public Integer getRunsScored() { return runsScored; }
    public void setRunsScored(Integer runsScored) { this.runsScored = runsScored; }
    public Integer getExtraRuns() { return extraRuns; }
    public void setExtraRuns(Integer extraRuns) { this.extraRuns = extraRuns; }
    public Boolean getIsWicket() { return isWicket; }
    public void setIsWicket(Boolean isWicket) { this.isWicket = isWicket; }
    public String getCommentaryText() { return commentaryText; }
    public void setCommentaryText(String commentaryText) { this.commentaryText = commentaryText; }
}
