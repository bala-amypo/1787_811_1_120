package com.example.demo.service;

import com.example.demo.entity.ApiKeyEntity;
import com.example.demo.entity.UserAccountEntity;

import java.util.List;
import java.util.Optional;

public interface ApiKeyService {
    ApiKeyEntity createKey(UserAccountEntity user, String planName);
    Optional<ApiKeyEntity> getKey(String key);
    List<ApiKeyEntity> getAllKeys();
}
