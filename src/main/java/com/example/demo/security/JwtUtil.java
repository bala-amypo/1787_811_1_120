package com.example.demo.security;

import io.jsonwebtoken.Claims;
import java.util.Map;

public class JwtUtil {
    public String generateToken(Map<String, Object> extraClaims, String subject) {
        return "TOKEN-" + subject;
    }
    public Claims getClaims(String token) { return null; }
    public String getUsername(String token) { return null; }
    public long getExpirationMillis() { return 3600000L; }
    public boolean isTokenValid(String token, String username) { return true; }
    
}
