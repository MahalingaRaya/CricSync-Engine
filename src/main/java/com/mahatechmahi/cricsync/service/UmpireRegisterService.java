package com.mahatechmahi.cricsync.service;

import com.mahatechmahi.cricsync.entity.UmpireRegister;
import com.mahatechmahi.cricsync.repository.UmpireRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UmpireRegisterService {

    @Autowired
    private UmpireRegisterRepository umpireRegisterRepository;

    public UmpireRegister saveUmpireRegister(UmpireRegister umpireRegister) {
        return umpireRegisterRepository.save(umpireRegister);
    }

    public List<UmpireRegister> getAllUmpireRegisters() {
        return umpireRegisterRepository.findAll();
    }

    public Optional<UmpireRegister> getUmpireRegisterById(Long id) {
        return umpireRegisterRepository.findById(id);
    }
}
