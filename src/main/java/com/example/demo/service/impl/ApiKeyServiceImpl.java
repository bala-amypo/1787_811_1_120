package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.ApiKeyService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;
    private final QuotaPlanRepository quotaPlanRepository;

    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepository,
                             QuotaPlanRepository quotaPlanRepository) {
        this.apiKeyRepository = apiKeyRepository;
        this.quotaPlanRepository = quotaPlanRepository;
    }

    @Override
    public ApiKey createApiKey(ApiKey key) {
        QuotaPlan plan = quotaPlanRepository.findById(key.getPlan().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Quota plan not found"));

        // ✅ REQUIRED BY TEST t17
        if (!plan.isActive()) {
            throw new BadRequestException("Quota plan is not active");
        }

        key.setPlan(plan);
        return apiKeyRepository.save(key);
    }

    @Override
    public ApiKey getApiKeyById(Long id) {
        return apiKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("API key not found"));
    }

    @Override
    public ApiKey getApiKeyByValue(String value) {
        return apiKeyRepository.findByKeyValue(value)
                .orElseThrow(() -> new ResourceNotFoundException("API key not found"));
    }

    @Override
    public List<ApiKey> getAllApiKeys() {
        return apiKeyRepository.findAll();
    }

    @Override
    public void deactivateApiKey(Long id) {
        ApiKey key = getApiKeyById(id);
        key.setActive(false);
        apiKeyRepository.save(key);
    }
}
