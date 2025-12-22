package com.example.demo.service;

import com.example.demo.entity.QuotaPlanEntity;

import java.util.List;

public interface QuotaPlanService {

    QuotaPlanEntity createPlan(QuotaPlanEntity plan);

    List<QuotaPlanEntity> getAllPlans();

    QuotaPlanEntity getPlanById(Long id);
}
