package com.example.demo.service.impl;

import com.example.demo.entity.ApiKeyEntity;
import com.example.demo.entity.UserAccountEntity;
import com.example.demo.entity.QuotaPlanEntity;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.ApiKeyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;
    private final QuotaPlanRepository quotaPlanRepository;

    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepository, QuotaPlanRepository quotaPlanRepository) {
        this.apiKeyRepository = apiKeyRepository;
        this.quotaPlanRepository = quotaPlanRepository;
    }

    @Override
    public ApiKeyEntity createKey(UserAccountEntity user, String planName) {
        ApiKeyEntity key = new ApiKeyEntity();
        key.setKey(UUID.randomUUID().toString());
        key.setOwner(user);

        QuotaPlanEntity plan = quotaPlanRepository.findAll()
                .stream()
                .filter(p -> p.getName().equalsIgnoreCase(planName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        key.setPlan(plan);
        return apiKeyRepository.save(key);
    }

    @Override
    public Optional<ApiKeyEntity> getKey(String key) {
        return apiKeyRepository.findByKey(key);
    }

    @Override
    public List<ApiKeyEntity> getAllKeys() {
        return apiKeyRepository.findAll();
    }
}
