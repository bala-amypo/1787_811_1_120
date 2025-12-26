package com.example.demo.service.impl;

import com.example.demo.entity.KeyExemption;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ApiKeyRepository;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.KeyExemptionService;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository keyExemptionRepository;
    private final ApiKeyRepository apiKeyRepository;

    public KeyExemptionServiceImpl(KeyExemptionRepository keyExemptionRepository,
                                   ApiKeyRepository apiKeyRepository) {
        this.keyExemptionRepository = keyExemptionRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public KeyExemption createExemption(KeyExemption exemption) {
        apiKeyRepository.findById(exemption.getApiKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("API key not found"));

        if (exemption.getTemporaryExtensionLimit() != null &&
                exemption.getTemporaryExtensionLimit() < 0) {
            throw new BadRequestException("Invalid temporary extension limit");
        }

        if (exemption.getValidUntil() != null &&
                exemption.getValidUntil().isBefore(Instant.now())) {
            throw new BadRequestException("Exemption already expired");
        }

        return keyExemptionRepository.save(exemption);
    }

    @Override
    public KeyExemption getExemptionByKey(Long keyId) {
        return keyExemptionRepository.findByApiKey_Id(keyId)
                .orElseThrow(() -> new ResourceNotFoundException("Exemption not found"));
    }

    @Override
    public List<KeyExemption> getAllExemptions() {
        return keyExemptionRepository.findAll();
    }
}
