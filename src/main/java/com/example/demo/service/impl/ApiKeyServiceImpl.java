package com.example.demo.service.impl;

import com.example.demo.entity.ApiKeyEntity;
import com.example.demo.entity.QuotaPlanEntity;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.ApiKeyService;
import com.example.demo.util.ApiKeyGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiKeyRepo;
    private final QuotaPlanRepository planRepo;

    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepo,
                             QuotaPlanRepository planRepo) {
        this.apiKeyRepo = apiKeyRepo;
        this.planRepo = planRepo;
    }

    @Override
    public ApiKeyEntity generateKey(Long userId, Long planId) {
        QuotaPlanEntity plan = planRepo.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        ApiKeyEntity key = new ApiKeyEntity();
        key.setOwnerId(userId);
        key.setPlan(plan);
        key.setKeyValue(ApiKeyGenerator.generate());
        return apiKeyRepo.save(key);
    }

    @Override
    public List<ApiKeyEntity> getKeysByUser(Long userId) {
        return apiKeyRepo.findByOwnerId(userId);
    }

    @Override
    public ApiKeyEntity getByKeyValue(String keyValue) {
        return apiKeyRepo.findByKeyValue(keyValue)
                .orElseThrow(() -> new RuntimeException("API Key not found"));
    }

    @Override
    public void deactivateKey(Long keyId) {
        ApiKeyEntity key = apiKeyRepo.findById(keyId)
                .orElseThrow(() -> new RuntimeException("API Key not found"));
        key.setActive(false);
        apiKeyRepo.save(key);
    }
}
