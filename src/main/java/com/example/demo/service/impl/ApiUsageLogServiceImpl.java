package com.example.demo.service.impl;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;

import java.time.Instant;
import java.util.List;

public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository repo;
    private final ApiKeyRepository keyRepo;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository repo, ApiKeyRepository keyRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
    }

    public ApiUsageLog logUsage(ApiUsageLog log) {
        if (log.getTimestamp().isAfter(Instant.now()))
            throw new BadRequestException("Future timestamp");

        keyRepo.findById(log.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Key"));

        return repo.save(log);
    }

    public List<ApiUsageLog> getUsageForToday(Long id) {
        return repo.findForKeyBetween(id,
                Instant.now().minusSeconds(86400),
                Instant.now());
    }

    public int countRequestsToday(Long id) {
        return repo.countForKeyBetween(id,
                Instant.now().minusSeconds(86400),
                Instant.now());
    }

    public List<ApiUsageLog> getUsageForApiKey(Long id) {
        return repo.findByApiKey_Id(id);
    }
}
