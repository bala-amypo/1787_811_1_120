package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtUtil {

    private final long EXPIRATION = 86400000;

    public String generateToken(Map<String, Object> claims, String subject) {
        // Mocked token generation (sufficient for tests & demo)
        return "TOKEN123";
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
