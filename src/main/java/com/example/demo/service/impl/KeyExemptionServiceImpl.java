package com.example.demo.service.impl;

import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.KeyExemptionService;

import java.time.Instant;
import java.util.List;

public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repo;
    private final ApiKeyRepository keyRepo;

    public KeyExemptionServiceImpl(KeyExemptionRepository repo,
                                   ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    public KeyExemption createExemption(KeyExemption e) {
        keyRepo.findById(e.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Key missing"));

        if (e.getTemporaryExtensionLimit() != null &&
                e.getTemporaryExtensionLimit() < 0)
            throw new BadRequestException("Invalid extension");

        if (e.getValidUntil() != null &&
                e.getValidUntil().isBefore(Instant.now()))
            throw new BadRequestException("Expired");

        return repo.save(e);
    }

    public KeyExemption getExemptionByKey(Long keyId) {
        return repo.findByApiKey_Id(keyId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    public List<KeyExemption> getAllExemptions() {
        return repo.findAll();
    }
}
