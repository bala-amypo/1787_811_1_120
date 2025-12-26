package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class KeyExemptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ApiKeyEntity apiKey;

    private String reason;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ApiKeyEntity getApiKey() { return apiKey; }
    public void setApiKey(ApiKeyEntity apiKey) { this.apiKey = apiKey; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
