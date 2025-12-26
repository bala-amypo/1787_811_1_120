package com.example.demo.controller;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.RateLimitEnforcement;
import com.example.demo.service.ApiKeyService;
import com.example.demo.repository.RateLimitEnforcementRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rate-limits")
public class RateLimitEnforcementController {

    private final ApiKeyService apiKeyService;
    private final RateLimitEnforcementRepository enforcementRepo;

    public RateLimitEnforcementController(ApiKeyService apiKeyService,
                                          RateLimitEnforcementRepository enforcementRepo) {
        this.apiKeyService = apiKeyService;
        this.enforcementRepo = enforcementRepo;
    }

    @GetMapping("/{apiKeyValue}")
    public List<RateLimitEnforcementEntity> getByApiKey(
            @PathVariable String apiKeyValue) {

        ApiKeyEntity apiKey = apiKeyService.getByKeyValue(apiKeyValue);
        return enforcementRepo.findByApiKey(apiKey);
    }
}
