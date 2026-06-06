package com.mahatechmahi.cricsync.service;

import com.mahatechmahi.cricsync.entity.MarketplaceJob;
import com.mahatechmahi.cricsync.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public MarketplaceJob saveJob(MarketplaceJob job) {
        return jobRepository.save(job);
    }

    public List<MarketplaceJob> getAllJobs() {
        return jobRepository.findAll();
    }

    public Optional<MarketplaceJob> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
