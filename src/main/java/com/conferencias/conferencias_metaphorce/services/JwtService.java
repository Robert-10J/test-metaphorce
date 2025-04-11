package com.conferencias.conferencias_metaphorce.services;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;


@Service
public class JwtService {

    @Value("${jwt.secret}")

    private String secret;
    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String participante) {
        return Jwts.builder()
                .setSubject(participante)
                .setIssuedAt(new java.util.Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) 
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
 
    public String extractParticipante(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            extractParticipante(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
