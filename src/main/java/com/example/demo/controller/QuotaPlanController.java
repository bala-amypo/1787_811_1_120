package com.example.demo.controller;

import com.example.demo.entity.QuotaPlan;
import com.example.demo.service.QuotaPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/quota-plans")
public class QuotaPlanController {
    @Autowired private QuotaPlanService quotaPlanService;

    @PostMapping
    public ResponseEntity<QuotaPlan> create(@RequestBody QuotaPlan plan) {
        return ResponseEntity.ok(quotaPlanService.createQuotaPlan(plan));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuotaPlan> getById(@PathVariable Long id) {
        return ResponseEntity.ok(quotaPlanService.getQuotaPlanById(id));
    }

    @GetMapping
    public ResponseEntity<List<QuotaPlan>> getAll() {
        return ResponseEntity.ok(quotaPlanService.getAllPlans());
    }
}
