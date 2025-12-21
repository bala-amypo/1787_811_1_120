package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.sql.Timestamp;

@Entity
@Table(name = "rate_limit_enforcement")
public class RateLimitEnforcementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_key_id", nullable = false)
    private ApiKeyEntity apiKey;

    @Column(nullable = false)
    private Timestamp blockedAt;

    @Min(1)
    @Column(nullable = false)
    private Integer limitExceededBy;

    @Column(columnDefinition = "TEXT")
    private String message;

    public Long getId() { return id; }
    public ApiKeyEntity getApiKey() { return apiKey; }
    public void setApiKey(ApiKeyEntity apiKey) { this.apiKey = apiKey; }

    public Timestamp getBlockedAt() { return blockedAt; }
    public void setBlockedAt(Timestamp blockedAt) { this.blockedAt = blockedAt; }

    public Integer getLimitExceededBy() { return limitExceededBy; }
    public void setLimitExceededBy(Integer limitExceededBy) {
        this.limitExceededBy = limitExceededBy;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
