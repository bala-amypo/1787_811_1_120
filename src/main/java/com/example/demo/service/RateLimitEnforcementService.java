package com.example.demo.service;

import com.example.demo.entity.ApiKeyEntity;

public interface RateLimitEnforcementService {

    void enforceLimit(ApiKeyEntity apiKey, long currentUsage);
}
