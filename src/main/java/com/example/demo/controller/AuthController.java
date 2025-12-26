package com.example.demo.controller;

import com.example.demo.security.JwtUtil;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final JwtUtil jwtUtil = new JwtUtil();
    // Empty - just to compile
}
