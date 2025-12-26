package com.example.demo.repository;

import com.example.demo.entity.RateLimitEnforcementEntity;
import com.example.demo.entity.ApiKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface RateLimitEnforcementRepository extends JpaRepository<RateLimitEnforcementEntity, Long> {
    Optional<RateLimitEnforcementEntity> findByApiKeyAndDate(ApiKeyEntity apiKey, LocalDate date);
}
