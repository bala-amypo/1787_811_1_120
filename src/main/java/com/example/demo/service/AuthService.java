package com.example.demo.service;

import com.example.demo.entity.UserAccountEntity;

public interface AuthService {

    UserAccountEntity register(String email, String password, String role);

    String authenticate(String email, String password);
}
