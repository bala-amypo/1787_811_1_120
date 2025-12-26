package com.example.demo.service.impl;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository apiUsageLogRepository;
    private final ApiKeyRepository apiKeyRepository;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository apiUsageLogRepository,
                                  ApiKeyRepository apiKeyRepository) {
        this.apiUsageLogRepository = apiUsageLogRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public ApiUsageLog logUsage(ApiUsageLog log) {
        apiKeyRepository.findById(log.getApiKey().getId())
                .orElseThrow(() -> new BadRequestException("Invalid API key"));

        if (log.getTimestamp().isAfter(Instant.now())) {
            throw new BadRequestException("Future timestamp not allowed");
        }

        return apiUsageLogRepository.save(log);
    }

    @Override
    public List<ApiUsageLog> getUsageForApiKey(Long keyId) {
        return apiUsageLogRepository.findByApiKey_Id(keyId);
    }

    @Override
    public List<ApiUsageLog> getUsageForToday(Long keyId) {
        Instant start = Instant.now().truncatedTo(ChronoUnit.DAYS);
        Instant end = Instant.now();
        return apiUsageLogRepository.findForKeyBetween(keyId, start, end);
    }

    @Override
    public int countRequestsToday(Long keyId) {
        Instant start = Instant.now().truncatedTo(ChronoUnit.DAYS);
        Instant end = Instant.now();
        Integer count = apiUsageLogRepository.countForKeyBetween(keyId, start, end);
        return count == null ? 0 : count;
    }
}
