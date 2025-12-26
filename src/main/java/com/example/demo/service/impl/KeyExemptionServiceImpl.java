// src/main/java/com/example/demo/service/impl/KeyExemptionServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;

public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository exemptionRepo;
    private final ApiKeyRepository apiKeyRepo;

    public KeyExemptionServiceImpl(KeyExemptionRepository exemptionRepo,
                                   ApiKeyRepository apiKeyRepo) {
        this.exemptionRepo = exemptionRepo;
        this.apiKeyRepo = apiKeyRepo;
    }

    @Override
    public KeyExemption createExemption(KeyExemption e) {
        Long keyId = e.getApiKey() != null ? e.getApiKey().getId() : null;
        if (keyId == null) {
            throw new BadRequestException("ApiKey required");
        }
        ApiKey key = apiKeyRepo.findById(keyId)
                .orElseThrow(ResourceNotFoundException::new);
        if (e.getTemporaryExtensionLimit() < 0) {
            throw new BadRequestException("Temporary extension limit must be >= 0");
        }
        e.setApiKey(key);
        return exemptionRepo.save(e);
    }

    @Override
    public KeyExemption getExemptionByKey(Long keyId) {
        return exemptionRepo.findByApiKey_Id(keyId)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
