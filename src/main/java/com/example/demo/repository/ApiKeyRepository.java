package com.example.demo.repository;

import com.example.demo.entity.ApiKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApiKeyRepository extends JpaRepository<ApiKeyEntity, Long> {

    Optional<ApiKeyEntity> findByKeyValue(String keyValue);

    List<ApiKeyEntity> findByOwnerId(Long ownerId);

    boolean existsByKeyValue(String keyValue);
}

