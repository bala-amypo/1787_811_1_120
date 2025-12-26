package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quota_plans")
public class QuotaPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String planName;
    @Column(nullable = false)
    private int dailyLimit;
    @Column(nullable = false)
    private boolean active = true;

    public QuotaPlan() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getPlanName() { return planName; } public void setPlanName(String planName) { this.planName = planName; }
    public int getDailyLimit() { return dailyLimit; } public void setDailyLimit(int dailyLimit) { this.dailyLimit = dailyLimit; }
    public boolean isActive() { return active; } public void setActive(boolean active) { this.active = active; }
}
