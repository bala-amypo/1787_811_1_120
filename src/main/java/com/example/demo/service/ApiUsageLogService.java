package com.example.demo.service;

import com.example.demo.entity.ApiKeyEntity;

import java.sql.Timestamp;

public interface ApiUsageLogService {

    void logUsage(ApiKeyEntity apiKey, String endpoint);

    long countUsageToday(ApiKeyEntity apiKey);

    long countUsageBetween(ApiKeyEntity apiKey, Timestamp start, Timestamp end);
}
