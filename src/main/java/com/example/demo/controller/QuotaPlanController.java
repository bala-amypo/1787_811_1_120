package com.example.demo.controller;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.service.QuotaPlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quota-plans")
public class QuotaPlanController {

    private final QuotaPlanService planService;

    public QuotaPlanController(QuotaPlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    public QuotaPlanEntity create(@RequestBody QuotaPlanEntity plan) {
        return planService.createPlan(plan);
    }

    @GetMapping
    public List<QuotaPlanEntity> getAll() {
        return planService.getAllPlans();
    }

    @GetMapping("/{id}")
    public QuotaPlanEntity getById(@PathVariable Long id) {
        return planService.getPlanById(id);
    }
}
