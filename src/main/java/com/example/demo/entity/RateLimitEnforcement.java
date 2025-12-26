package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rate_limit_enforcements")
public class RateLimitEnforcement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotNull @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "api_key_id", nullable = false) private ApiKey apiKey;
    @NotNull @Min(1) @Column(nullable = false) private int limitExceededBy;
    @NotBlank @Column(nullable = false) private String message;

    public RateLimitEnforcement() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public ApiKey getApiKey() { return apiKey; } public void setApiKey(ApiKey apiKey) { this.apiKey = apiKey; }
    public int getLimitExceededBy() { return limitExceededBy; } public void setLimitExceededBy(int limitExceededBy) { this.limitExceededBy = limitExceededBy; }
    public String getMessage() { return message; } public void setMessage(String message) { this.message = message; }
}
