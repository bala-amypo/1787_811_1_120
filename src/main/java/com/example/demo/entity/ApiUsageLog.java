package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "api_usage_logs")
public class ApiUsageLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotNull @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "api_key_id", nullable = false) private ApiKey apiKey;
    @NotBlank @Column(nullable = false) private String endpoint;
    @NotNull @Column(nullable = false) private Instant timestamp = Instant.now();

    public ApiUsageLog() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public ApiKey getApiKey() { return apiKey; } public void setApiKey(ApiKey apiKey) { this.apiKey = apiKey; }
    public String getEndpoint() { return endpoint; } public void setEndpoint(String endpoint) { this.endpoint = endpoint; }
    public Instant getTimestamp() { return timestamp; } public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}
