package com.example.demo.repository;

import com.example.demo.entity.ApiKeyEntity;
import com.example.demo.entity.ApiUsageLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ApiUsageLogRepository extends JpaRepository<ApiUsageLogEntity, Long> {

    List<ApiUsageLogEntity> findByApiKey(ApiKeyEntity apiKey);

    long countByApiKeyAndTimestampBetween(
            ApiKeyEntity apiKey,
            Timestamp start,
            Timestamp end
    );
}

