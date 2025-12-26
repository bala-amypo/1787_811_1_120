package com.example.demo.service.impl;

import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.RateLimitEnforcementService;

import java.util.List;

public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository repo;
    private final ApiKeyRepository keyRepo;

    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository repo,
                                          ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    public RateLimitEnforcement createEnforcement(RateLimitEnforcement e) {
        keyRepo.findById(e.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Key missing"));

        if (e.getLimitExceededBy() < 1)
            throw new BadRequestException("Invalid limit");

        return repo.save(e);
    }

    public RateLimitEnforcement getEnforcementById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    public List<RateLimitEnforcement> getEnforcementsForKey(Long keyId) {
        return repo.findByApiKey_Id(keyId);
    }
}
