package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "key_exemptions")
public class KeyExemption {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotNull @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "api_key_id", nullable = false) private ApiKey apiKey;
    @NotNull @Min(0) @Column(name = "temporary_extension_limit", nullable = false) private int temporaryExtensionLimit;
    @NotNull @Column(name = "valid_until", nullable = false) private Instant validUntil;

    public KeyExemption() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public ApiKey getApiKey() { return apiKey; } public void setApiKey(ApiKey apiKey) { this.apiKey = apiKey; }
    public int getTemporaryExtensionLimit() { return temporaryExtensionLimit; } public void setTemporaryExtensionLimit(int temporaryExtensionLimit) { this.temporaryExtensionLimit = temporaryExtensionLimit; }
    public Instant getValidUntil() { return validUntil; } public void setValidUntil(Instant validUntil) { this.validUntil = validUntil; }
}
