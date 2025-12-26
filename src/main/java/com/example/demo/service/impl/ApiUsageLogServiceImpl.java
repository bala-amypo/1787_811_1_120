// src/main/java/com/example/demo/service/impl/ApiUsageLogServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.ApiUsageLog;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;

import java.time.*;
import java.util.List;

public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository usageRepo;
    private final ApiKeyRepository apiKeyRepo;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository usageRepo,
                                  ApiKeyRepository apiKeyRepo) {
        this.usageRepo = usageRepo;
        this.apiKeyRepo = apiKeyRepo;
    }

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {
        Long keyId = log.getApiKey() != null ? log.getApiKey().getId() : null;
        if (keyId == null) {
            throw new BadRequestException("ApiKey required");
        }
        ApiKey key = apiKeyRepo.findById(keyId)
                .orElseThrow(ResourceNotFoundException::new);
        if (log.getTimestamp() != null && log.getTimestamp().isAfter(Instant.now())) {
            throw new BadRequestException("Future timestamp not allowed");
        }
        log.setApiKey(key);
        return usageRepo.save(log);
    }

    private Instant startOfToday() {
        return LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
    }

    private Instant endOfToday() {
        return startOfToday().plusSeconds(24 * 60 * 60 - 1);
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(Long keyId) {
        return usageRepo.findForKeyBetween(keyId, startOfToday(), endOfToday());
    }

    @Override
    public int countRequestsToday(Long keyId) {
        return usageRepo.countForKeyBetween(keyId, startOfToday(), endOfToday());
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long keyId) {
        return usageRepo.findByApiKey_Id(keyId);
    }
}
