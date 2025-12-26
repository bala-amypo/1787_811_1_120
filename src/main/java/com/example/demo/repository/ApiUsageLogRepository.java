package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
import com.example.demo.entity.*;

import java.time.Instant;

public interface ApiUsageLogRepository extends JpaRepository<ApiUsageLog, Long> {
    List<ApiUsageLog> findByApiKey_Id(Long id);
    List<ApiUsageLog> findForKeyBetween(Long id, Instant start, Instant end);
    Integer countForKeyBetween(Long id, Instant start, Instant end);
}
