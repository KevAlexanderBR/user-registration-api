package com.kevin.user_registration_api.infrastructure.security;

import com.kevin.user_registration_api.domain.security.JwtTokenProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProviderImpl implements JwtTokenProvider {

    @Value("${jwt.key}")
    private String key;

    @Override
    public String generateToken() {
        return Jwts.builder()
                .setSubject("username")
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}
