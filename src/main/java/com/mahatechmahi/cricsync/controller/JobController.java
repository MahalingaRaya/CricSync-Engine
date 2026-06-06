package com.mahatechmahi.cricsync.controller;

import com.mahatechmahi.cricsync.entity.MarketplaceJob;
import com.mahatechmahi.cricsync.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public ResponseEntity<MarketplaceJob> createJob(@RequestBody MarketplaceJob job) {
        return ResponseEntity.ok(jobService.saveJob(job));
    }

    @GetMapping
    public ResponseEntity<List<MarketplaceJob>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarketplaceJob> getJobById(@PathVariable Long id) {
        return jobService.getJobById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}
