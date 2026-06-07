package com.mahatechmahi.cricsync.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "marketplace_jobs")
public class MarketplaceJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;
    private String league;
    private String location;
    private String date;
    private String fee;
    private String type;
    
    @Column(columnDefinition = "boolean default false")
    private Boolean applied = false;

    // --- GETTERS AND SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getLeague() { return league; }
    public void setLeague(String league) { this.league = league; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getFee() { return fee; }
    public void setFee(String fee) { this.fee = fee; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Boolean getApplied() { return applied; }
    public void setApplied(Boolean applied) { this.applied = applied; }
}
