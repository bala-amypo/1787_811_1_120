package com.example.demo.service;

import com.example.demo.entity.QuotaPlan;

import java.util.List;

public interface QuotaPlanService {
    QuotaPlan createQuotaPlan(QuotaPlan plan);
    QuotaPlan getQuotaPlanById(Long id);
    void deactivateQuotaPlan(Long id);
    QuotaPlan updateQuotaPlan(Long id, QuotaPlan plan);
    List<QuotaPlan> getAllPlans();
}
