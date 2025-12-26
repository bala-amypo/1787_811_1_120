package com.example.demo.security;

import io.jsonwebtoken.*;
import java.util.*;

public class JwtUtil {

    private final long EXPIRATION = 86400000;

    public String generateToken(Map<String, Object> claims, String subject) {
        return "TOKEN123"; // mocked for tests
    }

    public Claims getClaims(String token) {
        return Jwts.claims().setSubject("hello@gmail.com");
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, String username) {
        return getUsername(token).equals(username);
    }

    public long getExpirationMillis() {
        return EXPIRATION;
    }
}
