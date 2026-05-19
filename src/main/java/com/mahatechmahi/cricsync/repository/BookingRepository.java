package com.mahatechmahi.cricsync.repository;

import com.mahatechmahi.cricsync.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByProfessionalId(Long professionalId);
    List<Booking> findByMatchId(Long matchId);
}
