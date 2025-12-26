package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "quota_plans")
public class QuotaPlan {  // ← MUST be QuotaPlan (matches filename)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(nullable = false, unique = true) private String planName;
    @NotNull @Min(1) @Column(nullable = false) private int dailyLimit;
    @Column(nullable = false) private boolean active = true;

    public QuotaPlan() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getPlanName() { return planName; } public void setPlanName(String planName) { this.planName = planName; }
    public int getDailyLimit() { return dailyLimit; } public void setDailyLimit(int dailyLimit) { this.dailyLimit = dailyLimit; }
    public boolean isActive() { return active; } public void setActive(boolean active) { this.active = active; }
}
