// src/main/java/com/example/demo/service/RateLimitEnforcementService.java
package com.example.demo.service;

import com.example.demo.entity.RateLimitEnforcement;
import java.util.List;

public interface RateLimitEnforcementService {
    RateLimitEnforcement createEnforcement(RateLimitEnforcement e);
    List<RateLimitEnforcement> getEnforcementsForKey(Long keyId);
    RateLimitEnforcement getEnforcementById(Long id);
}
