// src/main/java/com/example/demo/service/impl/ApiKeyServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.ApiKeyService;

import java.util.List;

public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiKeyRepo;
    private final QuotaPlanRepository quotaPlanRepo;

    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepo,
                             QuotaPlanRepository quotaPlanRepo) {
        this.apiKeyRepo = apiKeyRepo;
        this.quotaPlanRepo = quotaPlanRepo;
    }

    @Override
    public ApiKey createApiKey(ApiKey key) {
        Long planId = key.getPlan() != null ? key.getPlan().getId() : null;
        if (planId == null) {
            throw new BadRequestException("Plan required");
        }
        QuotaPlan plan = quotaPlanRepo.findById(planId)
                .orElseThrow(ResourceNotFoundException::new);
        if (!plan.isActive()) {
            throw new BadRequestException("Plan not active");
        }
        return apiKeyRepo.save(key);
    }

    @Override
    public ApiKey getApiKeyById(Long id) {
        return apiKeyRepo.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deactivateApiKey(Long id) {
        ApiKey key = getApiKeyById(id);
        key.setActive(false);
        apiKeyRepo.save(key);
    }

    @Override
    public ApiKey getApiKeyByValue(String value) {
        return apiKeyRepo.findByKeyValue(value)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<ApiKey> getAllApiKeys() {
        return apiKeyRepo.findAll();
    }
}
