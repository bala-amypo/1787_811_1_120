package com.example.demo.controller;

import com.example.demo.entity.ApiKeyEntity;
import com.example.demo.service.ApiKeyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api-keys")
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    public ApiKeyController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @PostMapping
    public ApiKeyEntity generate(@RequestBody Map<String, Long> request) {
        return apiKeyService.generateKey(
                request.get("userId"),
                request.get("planId")
        );
    }

    @GetMapping("/user/{userId}")
    public List<ApiKeyEntity> getByUser(@PathVariable Long userId) {
        return apiKeyService.getKeysByUser(userId);
    }

    @DeleteMapping("/{keyId}")
    public void deactivate(@PathVariable Long keyId) {
        apiKeyService.deactivateKey(keyId);
    }
}
