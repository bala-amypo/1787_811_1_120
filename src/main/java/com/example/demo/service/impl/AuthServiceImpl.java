package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserAccountRepository userRepo,
                           PasswordEncoder encoder,
                           AuthenticationManager authManager,
                           JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void register(RegisterRequestDto dto) {
        if (userRepo.existsByEmail(dto.getEmail()))
            throw new BadRequestException("Email exists");

        UserAccount user = new UserAccount();
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());

        userRepo.save(user);
    }

    @Override
    public AuthResponseDto login(AuthRequestDto dto) {
        UserAccount user = userRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid"));

        String token = jwtUtil.generateToken(
                Map.of("role", user.getRole()),
                user.getEmail()
        );

        AuthResponseDto res = new AuthResponseDto();
        res.setEmail(user.getEmail());
        res.setRole(user.getRole());
        res.setToken(token);

        return res;
    }
}
