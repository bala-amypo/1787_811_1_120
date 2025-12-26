package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "api_keys")
public class ApiKey {  // ← MUST be ApiKey
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, unique = true, length = 255) private String keyValue;
    @NotNull @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "plan_id", nullable = false) private QuotaPlan plan;
    @Column(name = "owner_id") private Long ownerId;
    @Column(nullable = false) private boolean active = true;

    public ApiKey() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getKeyValue() { return keyValue; } public void setKeyValue(String keyValue) { this.keyValue = keyValue; }
    public QuotaPlan getPlan() { return plan; } public void setPlan(QuotaPlan plan) { this.plan = plan; }
    public Long getOwnerId() { return ownerId; } public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
    public boolean isActive() { return active; } public void setActive(boolean active) { this.active = active; }
}
