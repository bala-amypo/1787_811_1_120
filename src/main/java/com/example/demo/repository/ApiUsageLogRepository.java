package com.example.demo.repository;

import com.example.demo.entity.ApiUsageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface ApiUsageLogRepository extends JpaRepository<ApiUsageLog, Long> {

    List<ApiUsageLog> findByApiKey_Id(Long id);

    @Query("select u from ApiUsageLog u where u.apiKey.id=:id and u.timestamp between :s and :e")
    List<ApiUsageLog> findForKeyBetween(Long id, Instant s, Instant e);

    @Query("select count(u) from ApiUsageLog u where u.apiKey.id=:id and u.timestamp between :s and :e")
    int countForKeyBetween(Long id, Instant s, Instant e);
}
