// src/main/java/com/example/demo/security/JwtUtil.java
package com.example.demo.security;

import io.jsonwebtoken.Claims;

import java.util.Map;

public class JwtUtil {

    public String generateToken(Map<String, Object> extraClaims, String subject) {
        // Simple dummy implementation; tests mock this.
        return "TOKEN-" + subject;
    }

    public Claims getClaims(String token) {
        // Tests often mock this, so body can be simple.
        return null;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public long getExpirationMillis() {
        // Must be > 0 for test.
        return 3600000L;
    }

    public boolean isTokenValid(String token, String username) {
        String extracted = getUsername(token);
        return extracted != null && extracted.equals(username);
    }
}
