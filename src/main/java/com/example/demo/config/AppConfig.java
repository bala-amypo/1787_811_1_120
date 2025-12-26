package com.example.demo.config;

import com.example.demo.repository.*;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.*;
import com.example.demo.service.impl.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public AuthService authService(
            UserAccountRepository userRepo,
            PasswordEncoder encoder,
            AuthenticationManager manager,
            JwtUtil jwtUtil) {
        return new AuthServiceImpl(userRepo, encoder, manager, jwtUtil);
    }

    @Bean
    public QuotaPlanService quotaPlanService(QuotaPlanRepository repo) {
        return new QuotaPlanServiceImpl(repo);
    }

    @Bean
    public ApiKeyService apiKeyService(
            ApiKeyRepository keyRepo,
            QuotaPlanRepository planRepo) {
        return new ApiKeyServiceImpl(keyRepo, planRepo);
    }

    @Bean
    public ApiUsageLogService apiUsageLogService(
            ApiUsageLogRepository repo,
            ApiKeyRepository keyRepo) {
        return new ApiUsageLogServiceImpl(repo, keyRepo);
    }

    @Bean
    public RateLimitEnforcementService rateLimitEnforcementService(
            RateLimitEnforcementRepository repo,
            ApiKeyRepository keyRepo) {
        return new RateLimitEnforcementServiceImpl(repo, keyRepo);
    }

    @Bean
    public KeyExemptionService keyExemptionService(
            KeyExemptionRepository repo,
            ApiKeyRepository keyRepo) {
        return new KeyExemptionServiceImpl(repo, keyRepo);
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
