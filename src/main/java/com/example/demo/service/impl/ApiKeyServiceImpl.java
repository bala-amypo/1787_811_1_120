package com.example.demo.service.impl;

import com.example.demo.entity.ApiKeyEntity;
import com.example.demo.entity.QuotaPlanEntity;
import com.example.demo.entity.UserAccountEntity;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.ApiKeyService;

import java.util.List;
import java.util.UUID;

public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiKeyRepo;
    private final UserAccountRepository userRepo;
    private final QuotaPlanRepository planRepo;

    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepo,
                             UserAccountRepository userRepo,
                             QuotaPlanRepository planRepo) {
        this.apiKeyRepo = apiKeyRepo;
        this.userRepo = userRepo;
        this.planRepo = planRepo;
    }

    @Override
    public ApiKeyEntity generateKey(Long userId, Long planId) {

        UserAccountEntity user = userRepo.findById(userId).orElseThrow();
        QuotaPlanEntity plan = planRepo.findById(planId).orElseThrow();

        ApiKeyEntity key = new ApiKeyEntity();
        key.setKeyValue(UUID.randomUUID().toString());
        key.setOwner(user);
        key.setPlan(plan);

        return apiKeyRepo.save(key);
    }

    @Override
    public ApiKeyEntity getByKeyValue(String keyValue) {
        return apiKeyRepo.findByKeyValue(keyValue).orElseThrow();
    }

    @Override
    public List<ApiKeyEntity> getKeysByUser(Long userId) {
        return apiKeyRepo.findByOwner_Id(userId);
    }

    @Override
    public void deactivateKey(Long keyId) {
        ApiKeyEntity key = apiKeyRepo.findById(keyId).orElseThrow();
        key.setActive(false);
        apiKeyRepo.save(key);
    }

    @Override
    public List<ApiKeyEntity> getAllApiKeys() {
        return apiKeyRepo.findAll();
    }
}
