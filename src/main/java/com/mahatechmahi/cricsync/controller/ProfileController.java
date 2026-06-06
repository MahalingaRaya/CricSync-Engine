package com.mahatechmahi.cricsync.controller;

import com.mahatechmahi.cricsync.entity.Profile;
import com.mahatechmahi.cricsync.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping
    public Profile createProfile(@RequestBody Profile profile) {
        return profileService.saveProfile(profile);
    }

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        Optional<Profile> profile = profileService.getProfileById(id);
        return profile.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
