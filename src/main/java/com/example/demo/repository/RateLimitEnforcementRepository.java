package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;
import com.example.demo.entity.*;

public interface RateLimitEnforcementRepository extends JpaRepository<RateLimitEnforcement, Long> {
    List<RateLimitEnforcement> findByApiKey_Id(Long id);
}
