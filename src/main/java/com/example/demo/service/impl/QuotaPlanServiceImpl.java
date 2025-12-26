package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.QuotaPlanService;

@Service
public class QuotaPlanServiceImpl implements QuotaPlanService {

    @Override
    public int getQuota(String apiKey) {
        return 1000;
    }
}
