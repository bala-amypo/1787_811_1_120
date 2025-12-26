package com.example.demo.controller;

import com.example.demo.entity.ApiKey;
import com.example.demo.entity.UserAccount;
import com.example.demo.service.ApiKeyService;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keys")
public class ApiKeyController {

    private final ApiKeyService apiKeyService;
    private final UserAccountRepository userRepo;

    public ApiKeyController(ApiKeyService apiKeyService, UserAccountRepository userRepo) {
        this.apiKeyService = apiKeyService;
        this.userRepo = userRepo;
    }

    @PostMapping("/create")
    public ApiKeyEntity createKey(@RequestParam String username, @RequestParam String plan) {
        UserAccountEntity user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return apiKeyService.createKey(user, plan);
    }

    @GetMapping
    public List<ApiKeyEntity> getAllKeys() {
        return apiKeyService.getAllKeys();
    }
}
