package com.example.demo.service.impl;

import com.example.demo.entity.QuotaPlanEntity;
import com.example.demo.repository.QuotaPlanRepository;
import com.example.demo.service.QuotaPlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotaPlanServiceImpl implements QuotaPlanService {

    private final QuotaPlanRepository planRepo;

    public QuotaPlanServiceImpl(QuotaPlanRepository planRepo) {
        this.planRepo = planRepo;
    }

    @Override
    public QuotaPlanEntity createPlan(QuotaPlanEntity plan) {
        return planRepo.save(plan);
    }

    @Override
    public List<QuotaPlanEntity> getAllPlans() {
        return planRepo.findAll();
    }

    @Override
    public QuotaPlanEntity getPlanById(Long id) {
        return planRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
    }
}
