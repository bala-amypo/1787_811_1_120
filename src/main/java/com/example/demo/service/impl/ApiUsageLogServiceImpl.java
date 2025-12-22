package com.example.demo.service.impl;

import com.example.demo.entity.ApiKeyEntity;
import com.example.demo.entity.ApiUsageLogEntity;
import com.example.demo.repository.ApiUsageLogRepository;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository usageRepo;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository usageRepo) {
        this.usageRepo = usageRepo;
    }

    @Override
    public void logUsage(ApiKeyEntity apiKey, String endpoint) {
        ApiUsageLogEntity log = new ApiUsageLogEntity();
        log.setApiKey(apiKey);
        log.setEndpoint(endpoint);
        log.setTimestamp(new Timestamp(System.currentTimeMillis()));
        usageRepo.save(log);
    }

    @Override
    public long countUsageToday(ApiKeyEntity apiKey) {
        LocalDate today = LocalDate.now();
        Timestamp start = Timestamp.valueOf(today.atStartOfDay());
        Timestamp end = Timestamp.valueOf(today.plusDays(1).atStartOfDay());
        return usageRepo.countByApiKeyAndTimestampBetween(apiKey, start, end);
    }

    @Override
    public long countUsageBetween(ApiKeyEntity apiKey, Timestamp start, Timestamp end) {
        return usageRepo.countByApiKeyAndTimestampBetween(apiKey, start, end);
    }
}
