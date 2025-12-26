// src/main/java/com/example/demo/service/impl/QuotaPlanServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;

import java.util.List;

public class QuotaPlanServiceImpl implements QuotaPlanService {

    private final QuotaPlanRepository quotaPlanRepo;

    public QuotaPlanServiceImpl(QuotaPlanRepository quotaPlanRepo) {
        this.quotaPlanRepo = quotaPlanRepo;
    }

    @Override
    public QuotaPlan createQuotaPlan(QuotaPlan plan) {
        if (plan.getDailyLimit() <= 0) {
            throw new BadRequestException("Daily limit must be positive");
        }
        return quotaPlanRepo.save(plan);
    }

    @Override
    public QuotaPlan getQuotaPlanById(Long id) {
        return quotaPlanRepo.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deactivateQuotaPlan(Long id) {
        QuotaPlan plan = getQuotaPlanById(id);
        plan.setActive(false);
        quotaPlanRepo.save(plan);
    }

    @Override
    public QuotaPlan updateQuotaPlan(Long id, QuotaPlan updated) {
        QuotaPlan existing = getQuotaPlanById(id);
        existing.setPlanName(updated.getPlanName());
        existing.setDailyLimit(updated.getDailyLimit());
        existing.setActive(updated.isActive());
        return quotaPlanRepo.save(existing);
    }

    @Override
    public List<QuotaPlan> getAllPlans() {
        return quotaPlanRepo.findAll();
    }
}
