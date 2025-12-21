package com.example.demo.repository;

import com.example.demo.entity.ApiKeyEntity;
import com.example.demo.entity.KeyExemptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KeyExemptionRepository extends JpaRepository<KeyExemptionEntity, Long> {

    Optional<KeyExemptionEntity> findByApiKey(ApiKeyEntity apiKey);
}

