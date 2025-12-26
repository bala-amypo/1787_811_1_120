package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ApiKeyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String key;

    @ManyToOne
    private UserAccountEntity owner;

    @ManyToOne
    private QuotaPlanEntity plan;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public UserAccountEntity getOwner() { return owner; }
    public void setOwner(UserAccountEntity owner) { this.owner = owner; }

    public QuotaPlanEntity getPlan() { return plan; }
    public void setPlan(QuotaPlanEntity plan) { this.plan = plan; }
}
