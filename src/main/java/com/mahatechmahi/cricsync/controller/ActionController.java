package com.mahatechmahi.cricsync.controller;
import com.mahatechmahi.cricsync.entity.*;
import com.mahatechmahi.cricsync.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/actions")
@CrossOrigin(origins = "*")
public class ActionController {
    @Autowired private PlayerSpotRepository playerRepo;
    @Autowired private UmpireRegisterRepository umpireRepo;

    @PostMapping("/apply-player")
    public PlayerSpot applyPlayer(@RequestBody PlayerSpot spot) { return playerRepo.save(spot); }
    
    @GetMapping("/players")
    public List<PlayerSpot> getPlayers() { return playerRepo.findAll(); }
    
    @PostMapping("/register-umpire")
    public UmpireRegister registerUmpire(@RequestBody UmpireRegister reg) { return umpireRepo.save(reg); }
}
