package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.RateLimitEnforcementService;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    @Override
    public boolean allowRequest(String apiKey) {
        return true;
    }
}
