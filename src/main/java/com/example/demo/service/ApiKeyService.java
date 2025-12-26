package com.example.demo.service;

import com.example.demo.entity.ApiKeyEntity;
import java.util.List;

public interface ApiKeyService {

    ApiKeyEntity generateKey(Long userId, Long planId);

    ApiKeyEntity getByKeyValue(String keyValue);

    List<ApiKeyEntity> getKeysByUser(Long userId);

    void deactivateKey(Long keyId);

    List<ApiKeyEntity> getAllApiKeys();
}
