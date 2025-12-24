package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    // TEMP: hardcoded (for testing)
    public boolean authenticate(String username, String password) {
        return "admin".equals(username) && "admin123".equals(password);
    }
}
