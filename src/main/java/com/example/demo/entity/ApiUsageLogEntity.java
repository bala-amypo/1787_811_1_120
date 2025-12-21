package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "api_usage_log")
public class ApiUsageLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_key_id", nullable = false)
    private ApiKeyEntity apiKey;

    @Column(nullable = false)
    private String endpoint;

    @Column(nullable = false)
    private Timestamp timestamp;

    public Long getId() { return id; }
    public ApiKeyEntity getApiKey() { return apiKey; }
    public void setApiKey(ApiKeyEntity apiKey) { this.apiKey = apiKey; }

    public String getEndpoint() { return endpoint; }
    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

    public Timestamp getTimestamp() { return timestamp; }
    public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }
}
