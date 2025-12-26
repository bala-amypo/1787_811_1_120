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

    private final UserAccountRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserAccountRepository repo,
                           PasswordEncoder encoder,
                           AuthenticationManager authManager,
                           JwtUtil jwtUtil) {
        this.repo = repo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void register(RegisterRequestDto dto) {
        if (repo.existsByEmail(dto.getEmail()))
            throw new BadRequestException("Email exists");

        UserAccount u = new UserAccount();
        u.setEmail(dto.getEmail());
        u.setPassword(encoder.encode(dto.getPassword()));
        u.setRole("ROLE_" + dto.getRole());

        repo.save(u);
    }

    @Override
    public AuthResponseDto login(AuthRequestDto dto) {
        UserAccount user = repo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid user"));

        String token = jwtUtil.generateToken(Map.of(), user.getEmail());
        return new AuthResponseDto(token);
    }
}
