package com.example.demo.config;

import com.example.demo.repository.*;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.Authentication;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws org.springframework.security.core.AuthenticationException {
                return authentication;
            }
        };
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    public AuthServiceImpl authService(UserAccountRepository userRepo, PasswordEncoder passwordEncoder, 
                                     AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        return new AuthServiceImpl(userRepo, passwordEncoder, authenticationManager, jwtUtil);
    }

    @Bean
    public QuotaPlanServiceImpl quotaPlanService(QuotaPlanRepository quotaPlanRepo) {
        return new QuotaPlanServiceImpl(quotaPlanRepo);
    }

    @Bean
    public ApiKeyServiceImpl apiKeyService(ApiKeyRepository apiKeyRepo, QuotaPlanRepository quotaPlanRepo) {
        return new ApiKeyServiceImpl(apiKeyRepo, quotaPlanRepo);
    }

    @Bean
    public ApiUsageLogServiceImpl usageService(ApiUsageLogRepository usageRepo, ApiKeyRepository apiKeyRepo) {
        return new ApiUsageLogServiceImpl(usageRepo, apiKeyRepo);
    }

    @Bean
    public RateLimitEnforcementServiceImpl enforcementService(RateLimitEnforcementRepository enforceRepo, ApiKeyRepository apiKeyRepo) {
        return new RateLimitEnforcementServiceImpl(enforceRepo, apiKeyRepo);
    }

    @Bean
    public KeyExemptionServiceImpl exemptionService(KeyExemptionRepository exemptionRepo, ApiKeyRepository apiKeyRepo) {
        return new KeyExemptionServiceImpl(exemptionRepo, apiKeyRepo);
    }
}