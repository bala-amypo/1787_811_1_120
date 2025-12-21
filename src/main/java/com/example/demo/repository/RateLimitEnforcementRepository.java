package com.example.demo.repository;

import com.example.demo.entity.ApiKeyEntity;
import com.example.demo.entity.RateLimitEnforcementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateLimitEnforcementRepository
        extends JpaRepository<RateLimitEnforcementEntity, Long> {

    List<RateLimitEnforcementEntity> findByApiKey(ApiKeyEntity apiKey);
}

