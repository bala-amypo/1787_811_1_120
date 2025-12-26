// src/main/java/com/example/demo/service/ApiKeyService.java
package com.example.demo.service;

import com.example.demo.entity.ApiKey;
import java.util.List;

public interface ApiKeyService {
    ApiKey createApiKey(ApiKey key);
    ApiKey getApiKeyById(Long id);
    void deactivateApiKey(Long id);
    ApiKey getApiKeyByValue(String value);
    List<ApiKey> getAllApiKeys();
}
