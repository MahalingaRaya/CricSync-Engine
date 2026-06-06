package com.mahatechmahi.cricsync.service;

import com.mahatechmahi.cricsync.entity.Profile;
import com.mahatechmahi.cricsync.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }
    
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }
}
