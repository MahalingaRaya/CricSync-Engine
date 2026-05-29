package com.cricsync.engine.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "marketplace_jobs")
@Data
public class MarketplaceJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleRequired;
    private String leagueName;
    private String venue;
    private String payPerMatch;
    private Boolean isFilled = false;
}
