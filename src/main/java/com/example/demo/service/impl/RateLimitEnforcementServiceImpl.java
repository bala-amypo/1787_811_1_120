// src/main/java/com/example/demo/service/impl/RateLimitEnforcementServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;

import java.util.List;

public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository enforceRepo;
    private final ApiKeyRepository apiKeyRepo;

    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository enforceRepo,
                                           ApiKeyRepository apiKeyRepo) {
        this.enforceRepo = enforceRepo;
        this.apiKeyRepo = apiKeyRepo;
    }

    @Override
    public RateLimitEnforcement createEnforcement(RateLimitEnforcement e) {
        Long keyId = e.getApiKey() != null ? e.getApiKey().getId() : null;
        if (keyId == null) {
            throw new BadRequestException("ApiKey required");
        }
        ApiKey key = apiKeyRepo.findById(keyId)
                .orElseThrow(ResourceNotFoundException::new);
        if (e.getLimitExceededBy() <= 0) {
            throw new BadRequestException("limitExceededBy must be > 0");
        }
        e.setApiKey(key);
        return enforceRepo.save(e);
    }

    @Override
    public List<RateLimitEnforcement> getEnforcementsForKey(Long keyId) {
        return enforceRepo.findByApiKey_Id(keyId);
    }

    @Override
    public RateLimitEnforcement getEnforcementById(Long id) {
        return enforceRepo.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
