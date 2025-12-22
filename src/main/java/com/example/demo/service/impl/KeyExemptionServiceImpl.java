package com.example.demo.service.impl;

import com.example.demo.entity.ApiKeyEntity;
import com.example.demo.entity.KeyExemptionEntity;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository exemptionRepo;

    public KeyExemptionServiceImpl(KeyExemptionRepository exemptionRepo) {
        this.exemptionRepo = exemptionRepo;
    }

    @Override
    public KeyExemptionEntity getByApiKey(ApiKeyEntity apiKey) {
        return exemptionRepo.findByApiKey(apiKey).orElse(null);
    }

    @Override
    public boolean hasUnlimitedAccess(ApiKeyEntity apiKey) {
        KeyExemptionEntity ex = getByApiKey(apiKey);
        return ex != null && Boolean.TRUE.equals(ex.getUnlimitedAccess())
                && ex.getValidUntil().after(new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public int getTemporaryExtension(ApiKeyEntity apiKey) {
        KeyExemptionEntity ex = getByApiKey(apiKey);
        if (ex == null || ex.getValidUntil().before(new Timestamp(System.currentTimeMillis()))) {
            return 0;
        }
        return ex.getTemporaryExtensionLimit() == null ? 0 : ex.getTemporaryExtensionLimit();
    }
}
