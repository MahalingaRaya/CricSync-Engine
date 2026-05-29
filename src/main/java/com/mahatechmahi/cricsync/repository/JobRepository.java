package com.mahatechmahi.cricsync.repository;

import com.mahatechmahi.cricsync.entity.MarketplaceJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<MarketplaceJob, Long> {
}
