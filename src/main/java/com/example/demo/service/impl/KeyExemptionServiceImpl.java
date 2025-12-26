package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.KeyExemptionService;

@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    @Override
    public boolean isExempted(String apiKey) {
        return false;
    }
}
