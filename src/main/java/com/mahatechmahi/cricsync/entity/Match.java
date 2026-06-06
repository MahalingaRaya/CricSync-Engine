package com.mahatechmahi.cricsync.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @Column(name = "team_a", nullable = false, length = 100)
    private String teamA;

    @Column(name = "team_b", nullable = false, length = 100)
    private String teamB;

    @Column(length = 20)
    private String status = "UPCOMING";

    // DYNAMIC LIMITS: Pass 2, 5, 10, or 20 here when creating a match!
    @Column(name = "max_overs")
    private Integer maxOvers = 20;

    @Column(name = "max_wickets")
    private Integer maxWickets = 10;

    @Column(name = "runs_a")
    private Integer runsA = 0;

    @Column(name = "wickets_a")
    private Integer wicketsA = 0;

    @Column(name = "balls_a")
    private Integer ballsA = 0;

    @Column(name = "runs_b")
    private Integer runsB = 0;

    @Column(name = "wickets_b")
    private Integer wicketsB = 0;

    @Column(name = "balls_b")
    private Integer ballsB = 0;

    @Column(name = "current_innings")
    private Integer currentInnings = 1;

    @Column(name = "winner_id", length = 100)
    private String winnerId;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }
    public String getTeamA() { return teamA; }
    public void setTeamA(String teamA) { this.teamA = teamA; }
    public String getTeamB() { return teamB; }
    public void setTeamB(String teamB) { this.teamB = teamB; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Integer getMaxOvers() { return maxOvers; }
    public void setMaxOvers(Integer maxOvers) { this.maxOvers = maxOvers; }
    public Integer getMaxWickets() { return maxWickets; }
    public void setMaxWickets(Integer maxWickets) { this.maxWickets = maxWickets; }

    public Integer getRunsA() { return runsA; }
    public void setRunsA(Integer runsA) { this.runsA = runsA; }
    public Integer getWicketsA() { return wicketsA; }
    public void setWicketsA(Integer wicketsA) { this.wicketsA = wicketsA; }
    public Integer getBallsA() { return ballsA; }
    public void setBallsA(Integer ballsA) { this.ballsA = ballsA; }
    public Integer getRunsB() { return runsB; }
    public void setRunsB(Integer runsB) { this.runsB = runsB; }
    public Integer getWicketsB() { return wicketsB; }
    public void setWicketsB(Integer wicketsB) { this.wicketsB = wicketsB; }
    public Integer getBallsB() { return ballsB; }
    public void setBallsB(Integer ballsB) { this.ballsB = ballsB; }
    public Integer getCurrentInnings() { return currentInnings; }
    public void setCurrentInnings(Integer currentInnings) { this.currentInnings = currentInnings; }
    public String getWinnerId() { return winnerId; }
    public void setWinnerId(String winnerId) { this.winnerId = winnerId; }
}
