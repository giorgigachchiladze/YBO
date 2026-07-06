package com.example.YBO.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    @Value("${spring.jjwt.secret}")
    private String secret;

    @Value("${spring.jjwt.duration}")
    private long duration;

    //generates new token with given UserDetails object handwritten
    //secret key and duration
    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + duration);

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSecretKey())
                .compact();
    }

    //gets username out of only given token.
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    //checks if token is valid with its username and expiring state.
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractClaims(token).getSubject();

        return username.equalsIgnoreCase(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    //checks if token is expired.
    private boolean isTokenExpired(String token) {
        Date expiration = extractClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    //parses, verifies, and extracts the payload (claims) from a given JWT token.
    private Claims extractClaims(String token){

        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
