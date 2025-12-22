package com.example.demo.service.impl;

import com.example.demo.entity.ApiKeyEntity;
import com.example.demo.entity.RateLimitEnforcementEntity;
import com.example.demo.repository.RateLimitEnforcementRepository;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository enforcementRepo;

    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository enforcementRepo) {
        this.enforcementRepo = enforcementRepo;
    }

    @Override
    public void enforceLimit(ApiKeyEntity apiKey, long currentUsage) {
        int allowed = apiKey.getPlan().getDailyLimit();
        if (currentUsage > allowed) {
            RateLimitEnforcementEntity block = new RateLimitEnforcementEntity();
            block.setApiKey(apiKey);
            block.setBlockedAt(new Timestamp(System.currentTimeMillis()));
            block.setLimitExceededBy((int) (currentUsage - allowed));
            block.setMessage("Daily quota exceeded");
            enforcementRepo.save(block);
            throw new RuntimeException("Rate limit exceeded");
        }
    }
}
