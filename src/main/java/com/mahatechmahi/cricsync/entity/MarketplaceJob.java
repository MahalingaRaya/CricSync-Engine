package com.mahatechmahi.cricsync.entity; // Updated package path

import jakarta.persistence.*;

@Entity
@Table(name = "marketplace_jobs")
public class MarketplaceJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleRequired;
    private String leagueName;
    private String venue;
    private String payPerMatch;
    private Boolean isFilled = false;

    // Explicit Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoleRequired() { return roleRequired; }
    public void setRoleRequired(String roleRequired) { this.roleRequired = roleRequired; }

    public String getLeagueName() { return leagueName; }
    public void setLeagueName(String leagueName) { this.leagueName = leagueName; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public String getPayPerMatch() { return payPerMatch; }
    public void setPayPerMatch(String payPerMatch) { this.payPerMatch = payPerMatch; }

    public Boolean getIsFilled() { return isFilled; }
    public void setIsFilled(Boolean isFilled) { this.isFilled = isFilled; }
}
