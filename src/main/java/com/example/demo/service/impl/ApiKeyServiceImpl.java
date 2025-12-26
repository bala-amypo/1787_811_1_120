package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.*;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.ApiKeyService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {


    private final ApiKeyRepository keyRepo;
    private final QuotaPlanRepository planRepo;

    public ApiKeyServiceImpl(ApiKeyRepository keyRepo, QuotaPlanRepository planRepo) {
        this.keyRepo = keyRepo;
        this.planRepo = planRepo;
    }

    public ApiKey createApiKey(ApiKey key) {
        QuotaPlan plan = planRepo.findById(key.getPlan().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found"));

        if (!plan.isActive())
            throw new BadRequestException("Plan inactive");

        key.setPlan(plan);
        return keyRepo.save(key);
    }

    public ApiKey getApiKeyById(Long id) {
        return keyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));
    }

    public ApiKey getApiKeyByValue(String value) {
        return keyRepo.findByKeyValue(value)
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));
    }

    public List<ApiKey> getAllApiKeys() {
        return keyRepo.findAll();
    }

    public void deactivateApiKey(Long id) {
        ApiKey key = getApiKeyById(id);
        key.setActive(false);
    }
}
