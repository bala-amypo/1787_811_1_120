// src/main/java/com/example/demo/repository/ApiKeyRepository.java
package com.example.demo.repository;

import com.example.demo.entity.ApiKey;
import java.util.List;
import java.util.Optional;

public interface ApiKeyRepository {
    ApiKey save(ApiKey apiKey);
    Optional<ApiKey> findById(Long id);
    Optional<ApiKey> findByKeyValue(String keyValue);
    List<ApiKey> findAll();
}
