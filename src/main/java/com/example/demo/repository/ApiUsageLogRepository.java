package com.example.demo.repository;

import com.example.demo.entity.ApiUsageLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiUsageLogRepository extends JpaRepository<ApiUsageLogEntity, Long> {
}
