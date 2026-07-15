package com.example.Customer_Loyalty_Tier_Upgrader.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;
    private final long expirationMillis;

    public JwtUtil(@Value("${jwt.secret}") String secretKey,
                   @Value("${jwt.expiration}") long expirationMillis) {
        this.key = Keys.hmacShaKeyFor(hexStringToByteArray(secretKey));
        this.expirationMillis = expirationMillis;
    }

    public String generateToken(Long customerId, String email, String role) {
        return Jwts.builder()
                .claim("customerId", customerId)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(key)
                .compact();
    }

    public Claims validateToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
    }

    public String extractEmail(String token) {
        return (String) validateToken(token).get("email");
    }

    public Long extractCustomerId(String token) {
        Object id = validateToken(token).get("customerId");
        if (id instanceof Integer) return ((Integer) id).longValue();
        return (Long) id;
    }

    public String extractRole(String token) {
        return (String) validateToken(token).get("role");
    }

    private byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
}
