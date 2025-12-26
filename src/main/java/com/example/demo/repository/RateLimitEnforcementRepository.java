// src/main/java/com/example/demo/repository/RateLimitEnforcementRepository.java
package com.example.demo.repository;

import com.example.demo.entity.RateLimitEnforcement;
import java.util.List;
import java.util.Optional;

public interface RateLimitEnforcementRepository {
    RateLimitEnforcement save(RateLimitEnforcement e);
    Optional<RateLimitEnforcement> findById(Long id);
    List<RateLimitEnforcement> findByApiKey_Id(Long keyId);
}
