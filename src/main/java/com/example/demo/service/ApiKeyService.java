package com.example.demo.service;

import com.example.demo.entity.ApiKeyEntity;

import java.util.List;

public interface ApiKeyService {

    ApiKeyEntity generateKey(Long userId, Long planId);

    List<ApiKeyEntity> getKeysByUser(Long userId);

    ApiKeyEntity getByKeyValue(String keyValue);

    void deactivateKey(Long keyId);
}
