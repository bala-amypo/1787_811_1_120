package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public boolean authenticate(String apiKey) {
        return apiKey != null && !apiKey.isBlank();
    }
}
