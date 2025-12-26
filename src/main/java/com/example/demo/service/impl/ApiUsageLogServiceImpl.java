package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.ApiUsageLogService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository logRepo;
    private final ApiKeyRepository keyRepo;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository logRepo, ApiKeyRepository keyRepo) {
        this.logRepo = logRepo;
        this.keyRepo = keyRepo;
    }

    public ApiUsageLog logUsage(ApiUsageLog log) {
        keyRepo.findById(log.getApiKey().getId())
                .orElseThrow(() -> new BadRequestException("Key invalid"));

        if (log.getTimestamp().isAfter(Instant.now()))
            throw new BadRequestException("Future time");

        return logRepo.save(log);
    }

    public List<ApiUsageLog> getUsageForApiKey(Long keyId) {
        return logRepo.findByApiKey_Id(keyId);
    }

    public List<ApiUsageLog> getUsageForToday(Long keyId) {
        Instant start = Instant.now().truncatedTo(ChronoUnit.DAYS);
        Instant end = Instant.now();
        return logRepo.findForKeyBetween(keyId, start, end);
    }

    public int countRequestsToday(Long keyId) {
        Instant start = Instant.now().truncatedTo(ChronoUnit.DAYS);
        Instant end = Instant.now();
        return logRepo.countForKeyBetween(keyId, start, end);
    }
}
