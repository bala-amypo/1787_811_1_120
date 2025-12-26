package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class RateLimitEnforcementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ApiKeyEntity apiKey;

    private Long count;
    private LocalDate date;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ApiKeyEntity getApiKey() { return apiKey; }
    public void setApiKey(ApiKeyEntity apiKey) { this.apiKey = apiKey; }

    public Long getCount() { return count; }
    public void setCount(Long count) { this.count = count; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
