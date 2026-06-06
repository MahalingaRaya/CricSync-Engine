package com.mahatechmahi.cricsync.controller;

import com.mahatechmahi.cricsync.entity.PlayerSpot;
import com.mahatechmahi.cricsync.service.PlayerSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/player-spots")
public class PlayerSpotController {

    @Autowired
    private PlayerSpotService playerSpotService;

    @PostMapping
    public PlayerSpot createPlayerSpot(@RequestBody PlayerSpot playerSpot) {
        return playerSpotService.savePlayerSpot(playerSpot);
    }

    @GetMapping
    public List<PlayerSpot> getAllPlayerSpots() {
        return playerSpotService.getAllPlayerSpots();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerSpot> getPlayerSpotById(@PathVariable Long id) {
        Optional<PlayerSpot> playerSpot = playerSpotService.getPlayerSpotById(id);
        return playerSpot.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
