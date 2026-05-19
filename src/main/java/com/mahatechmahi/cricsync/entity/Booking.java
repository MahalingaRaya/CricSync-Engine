package com.mahatechmahi.cricsync.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professional_id")
    private User professional;

    @Column(name = "role_requested", nullable = false, length = 30)
    private String roleRequested;

    @Column(name = "booking_status", length = 20)
    private String bookingStatus = "PENDING";

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }
    public User getProfessional() { return professional; }
    public void setProfessional(User professional) { this.professional = professional; }
    public String getRoleRequested() { return roleRequested; }
    public void setRoleRequested(String roleRequested) { this.roleRequested = roleRequested; }
    public String getBookingStatus() { return bookingStatus; }
    public void setBookingStatus(String bookingStatus) { this.bookingStatus = bookingStatus; }
}
