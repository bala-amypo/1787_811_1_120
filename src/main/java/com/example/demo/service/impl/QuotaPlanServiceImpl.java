package com.example.demo.service.impl;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.exception.*;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;

import java.util.List;

public class QuotaPlanServiceImpl implements QuotaPlanService {

    private final QuotaPlanRepository repo;

    public QuotaPlanServiceImpl(QuotaPlanRepository repo) {
        this.repo = repo;
    }

    public QuotaPlan createQuotaPlan(QuotaPlan plan) {
        if (plan.getDailyLimit() <= 0)
            throw new BadRequestException("Invalid limit");
        return repo.save(plan);
    }

    public QuotaPlan getQuotaPlanById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    public QuotaPlan updateQuotaPlan(Long id, QuotaPlan plan) {
        QuotaPlan existing = getQuotaPlanById(id);
        existing.setPlanName(plan.getPlanName());
        existing.setDailyLimit(plan.getDailyLimit());
        return repo.save(existing);
    }

    public List<QuotaPlan> getAllPlans() {
        return repo.findAll();
    }

    public void deactivateQuotaPlan(Long id) {
        QuotaPlan plan = getQuotaPlanById(id);
        plan.setActive(false);
    }
}
