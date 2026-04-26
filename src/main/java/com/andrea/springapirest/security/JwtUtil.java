package com.andrea.springapirest.security;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	 private static final String SECRET = "MiClaveMuyLargaYSeguraFechaCreacion11042026";

	    //private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
	    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

	    private final long EXPIRATION = 1000 * 60 * 60; // 1 hora

	    public String generateToken(Integer userId, String email, List<String> roles) {
	        return Jwts.builder()
	                .setSubject(String.valueOf(userId))
	                .claim("email", email)
	                .claim("roles", roles)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
	                .signWith(key)
	                .compact();
	    }

	    public String extractUserId(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(key)
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	    }

	    public String extractEmail(String token) {
	        return (String) Jwts.parserBuilder()
	                .setSigningKey(key)
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .get("email");
	    }

	    public boolean validateToken(String token) {
	        try {
	            Jwts.parserBuilder()
	                .setSigningKey(key)
	                .build()
	                .parseClaimsJws(token);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }
	    public List<String> extractRoles(String token) {
	        Claims claims = Jwts.parserBuilder()
	                .setSigningKey(key)
	                .build()
	                .parseClaimsJws(token)
	                .getBody();

	        return claims.get("roles", List.class);
	    }
}
