package com.example.demo.controller;

import com.example.demo.entity.ApiKeyEntity;
import com.example.demo.entity.KeyExemptionEntity;
import com.example.demo.repository.KeyExemptionRepository;
import com.example.demo.service.ApiKeyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exemptions")
public class KeyExemptionController {

    private final ApiKeyService apiKeyService;
    private final KeyExemptionRepository exemptionRepo;

    public KeyExemptionController(ApiKeyService apiKeyService,
                                  KeyExemptionRepository exemptionRepo) {
        this.apiKeyService = apiKeyService;
        this.exemptionRepo = exemptionRepo;
    }

    @PostMapping("/{apiKeyValue}")
    public KeyExemptionEntity save(
            @PathVariable String apiKeyValue,
            @RequestBody KeyExemptionEntity exemption) {

        ApiKeyEntity apiKey = apiKeyService.getByKeyValue(apiKeyValue);
        exemption.setApiKey(apiKey);
        return exemptionRepo.save(exemption);
    }
}
