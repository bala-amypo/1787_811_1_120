package com.example.demo.repository;

import com.example.demo.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
    Optional<ApiKey> findByKeyValue(String keyValue);
    List<ApiKey> findAll();
    Optional<ApiKey> findById(Long id);
    ApiKey save(ApiKey apiKey);
}
