package com.mahatechmahi.cricsync.controller;

import com.mahatechmahi.cricsync.entity.UmpireRegister;
import com.mahatechmahi.cricsync.service.UmpireRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/umpire-registers")
public class UmpireRegisterController {

    @Autowired
    private UmpireRegisterService umpireRegisterService;

    @PostMapping
    public UmpireRegister createUmpireRegister(@RequestBody UmpireRegister umpireRegister) {
        return umpireRegisterService.saveUmpireRegister(umpireRegister);
    }

    @GetMapping
    public List<UmpireRegister> getAllUmpireRegisters() {
        return umpireRegisterService.getAllUmpireRegisters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UmpireRegister> getUmpireRegisterById(@PathVariable Long id) {
        Optional<UmpireRegister> umpireRegister = umpireRegisterService.getUmpireRegisterById(id);
        return umpireRegister.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
