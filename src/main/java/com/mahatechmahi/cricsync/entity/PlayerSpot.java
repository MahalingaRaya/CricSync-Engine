package com.mahatechmahi.cricsync.entity;
import jakarta.persistence.*;
@Entity @Table(name = "player_spots")
public class PlayerSpot {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String playerName; private String email; private String skillType; private String targetTeam;
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getPlayerName() { return playerName; } public void setPlayerName(String playerName) { this.playerName = playerName; }
    public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
    public String getSkillType() { return skillType; } public void setSkillType(String skillType) { this.skillType = skillType; }
    public String getTargetTeam() { return targetTeam; } public void setTargetTeam(String targetTeam) { this.targetTeam = targetTeam; }
}
