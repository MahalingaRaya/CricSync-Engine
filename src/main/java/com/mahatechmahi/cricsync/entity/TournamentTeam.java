package com.mahatechmahi.cricsync.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tournament_teams")
public class TournamentTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @Column(name = "team_name", nullable = false, length = 100)
    private String teamName;

    @Column(name = "matches_played")
    private Integer matchesPlayed = 0;

    @Column(name = "matches_won")
    private Integer matchesWon = 0;

    @Column(name = "matches_lost")
    private Integer matchesLost = 0;

    private Integer points = 0;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public Integer getMatchesPlayed() { return matchesPlayed; }
    public void setMatchesPlayed(Integer matchesPlayed) { this.matchesPlayed = matchesPlayed; }
    public Integer getMatchesWon() { return matchesWon; }
    public void setMatchesWon(Integer matchesWon) { this.matchesWon = matchesWon; }
    public Integer getMatchesLost() { return matchesLost; }
    public void setMatchesLost(Integer matchesLost) { this.matchesLost = matchesLost; }
    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }
}
