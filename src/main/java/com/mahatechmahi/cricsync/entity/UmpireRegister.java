package com.mahatechmahi.cricsync.entity;
import jakarta.persistence.*;
@Entity @Table(name = "umpire_registrations")
public class UmpireRegister {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String umpireName; private String email; private Integer experienceYears;
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getUmpireName() { return umpireName; } public void setUmpireName(String umpireName) { this.umpireName = umpireName; }
    public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
    public Integer getExperienceYears() { return experienceYears; } public void setExperienceYears(Integer experienceYears) { this.experienceYears = experienceYears; }
}
