package com.mahatechmahi.cricsync.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "match_players")
public class MatchPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long matchId; // Links the player to a specific game
    private String teamName; 
    private String playerName;
    
    // Batting Stats
    private int runsScored = 0;
    private int ballsFaced = 0;
    private int fours = 0;
    private int sixes = 0;
    
    // Bowling Stats
    private double oversBowled = 0.0;
    private int runsConceded = 0;
    private int wicketsTaken = 0;

    // --- GETTERS AND SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getMatchId() { return matchId; }
    public void setMatchId(Long matchId) { this.matchId = matchId; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public int getRunsScored() { return runsScored; }
    public void setRunsScored(int runsScored) { this.runsScored = runsScored; }

    public int getBallsFaced() { return ballsFaced; }
    public void setBallsFaced(int ballsFaced) { this.ballsFaced = ballsFaced; }

    public int getFours() { return fours; }
    public void setFours(int fours) { this.fours = fours; }

    public int getSixes() { return sixes; }
    public void setSixes(int sixes) { this.sixes = sixes; }

    public double getOversBowled() { return oversBowled; }
    public void setOversBowled(double oversBowled) { this.oversBowled = oversBowled; }

    public int getRunsConceded() { return runsConceded; }
    public void setRunsConceded(int runsConceded) { this.runsConceded = runsConceded; }

    public int getWicketsTaken() { return wicketsTaken; }
    public void setWicketsTaken(int wicketsTaken) { this.wicketsTaken = wicketsTaken; }
}
