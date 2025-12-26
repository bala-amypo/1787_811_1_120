package com.example.demo.repository;

import com.example.demo.entity.QuotaPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuotaPlanRepository extends JpaRepository<QuotaPlan, Long> {
    List<QuotaPlan> findAll();
    Optional<QuotaPlan> findById(Long id);
    QuotaPlan save(QuotaPlan plan);
}
