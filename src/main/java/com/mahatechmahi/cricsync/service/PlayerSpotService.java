package com.mahatechmahi.cricsync.service;

import com.mahatechmahi.cricsync.entity.PlayerSpot;
import com.mahatechmahi.cricsync.repository.PlayerSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerSpotService {

    @Autowired
    private PlayerSpotRepository playerSpotRepository;

    public PlayerSpot savePlayerSpot(PlayerSpot playerSpot) {
        return playerSpotRepository.save(playerSpot);
    }

    public List<PlayerSpot> getAllPlayerSpots() {
        return playerSpotRepository.findAll();
    }

    public Optional<PlayerSpot> getPlayerSpotById(Long id) {
        return playerSpotRepository.findById(id);
    }
}
