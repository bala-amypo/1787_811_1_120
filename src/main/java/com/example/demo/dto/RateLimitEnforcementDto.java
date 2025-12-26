package com.example.demo.dto;

public class RateLimitEnforcementDto {

    private Long apiKeyId;
    private Integer limitExceededBy;
    private String message;

    public Long getApiKeyId() { return apiKeyId; }
    public void setApiKeyId(Long apiKeyId) { this.apiKeyId = apiKeyId; }

    public Integer getLimitExceededBy() { return limitExceededBy; }
    public void setLimitExceededBy(Integer limitExceededBy) {
        this.limitExceededBy = limitExceededBy;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
