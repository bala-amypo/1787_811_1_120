package com.example.demo.controller;

import com.example.demo.entity.ApiKey;
import com.example.demo.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/api-keys")
public class ApiKeyController {
    @Autowired private ApiKeyService apiKeyService;

    @PostMapping
    public ResponseEntity<ApiKey> create(@RequestBody ApiKey key) {
        return ResponseEntity.ok(apiKeyService.createApiKey(key));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiKey> getById(@PathVariable Long id) {
        return ResponseEntity.ok(apiKeyService.getApiKeyById(id));
    }

    @GetMapping
    public ResponseEntity<List<ApiKey>> getAll() {
        return ResponseEntity.ok(apiKeyService.getAllApiKeys());
    }
}
