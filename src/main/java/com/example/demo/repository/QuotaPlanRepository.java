// src/main/java/com/example/demo/repository/QuotaPlanRepository.java
package com.example.demo.repository;

import com.example.demo.entity.QuotaPlan;
import java.util.List;
import java.util.Optional;

public interface QuotaPlanRepository {
    QuotaPlan save(QuotaPlan plan);
    Optional<QuotaPlan> findById(Long id);
    List<QuotaPlan> findAll();
}
