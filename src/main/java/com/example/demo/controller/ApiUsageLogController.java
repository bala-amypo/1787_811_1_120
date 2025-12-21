package com.example.demo.controller;

import com.example.demo.entity.ApiKeyEntity;
import com.example.demo.service.ApiKeyService;
import com.example.demo.service.ApiUsageLogService;
import com.example.demo.service.RateLimitEnforcementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usage")
public class ApiUsageLogController {

    private final ApiKeyService apiKeyService;
    private final ApiUsageLogService usageService;
    private final RateLimitEnforcementService rateLimitService;

    public ApiUsageLogController(ApiKeyService apiKeyService,
                                 ApiUsageLogService usageService,
                                 RateLimitEnforcementService rateLimitService) {
        this.apiKeyService = apiKeyService;
        this.usageService = usageService;
        this.rateLimitService = rateLimitService;
    }

    @PostMapping("/hit")
    public String recordHit(
            @RequestHeader("X-API-KEY") String apiKeyValue,
            @RequestParam String endpoint) {

        ApiKeyEntity apiKey = apiKeyService.getByKeyValue(apiKeyValue);
        long usageToday = usageService.countUsageToday(apiKey);
        rateLimitService.enforceLimit(apiKey, usageToday + 1);
        usageService.logUsage(apiKey, endpoint);
        return "REQUEST_ACCEPTED";
    }
}
