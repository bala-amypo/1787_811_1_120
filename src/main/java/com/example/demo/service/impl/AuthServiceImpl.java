package com.example.demo.service.impl;

import com.example.demo.entity.UserAccountEntity;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserAccountRepository userRepo,
                           PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAccountEntity register(String email, String password, String role) {
        UserAccountEntity user = new UserAccountEntity();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        return userRepo.save(user);
    }

    @Override
    public String authenticate(String email, String password) {
        UserAccountEntity user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return "AUTH_SUCCESS";
    }
}
