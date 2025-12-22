package com.example.demo.service;

import com.example.demo.entity.ApiKeyEntity;
import com.example.demo.entity.KeyExemptionEntity;

public interface KeyExemptionService {

    KeyExemptionEntity getByApiKey(ApiKeyEntity apiKey);

    boolean hasUnlimitedAccess(ApiKeyEntity apiKey);

    int getTemporaryExtension(ApiKeyEntity apiKey);
}
