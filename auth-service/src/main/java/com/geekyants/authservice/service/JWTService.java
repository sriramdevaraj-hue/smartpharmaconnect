package com.geekyants.authservice.service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	private final static String SECRET = "MySecretPwd2025jyhbvfrb132478nbhfyhtg";
	private final static int TOKEN_EXPIRY = 3600000;

	public static String generateToken(Map<String, Object> extraClaims, UserDetails user) {
		Instant now = Instant.now();
		return Jwts.builder().setClaims(extraClaims).setSubject(user.getUsername()).setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plusMillis(TOKEN_EXPIRY)))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}

	private static Key getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}
	
	
	
	
	
	

	private static Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith((SecretKey) getSigningKey()).build().parseSignedClaims(token).getPayload();
	}

	public static String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}

	public static Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}

	public static boolean isTokenExpired(String token) {
		try {
			return extractExpiration(token).before(new Date());
		} catch (ExpiredJwtException ex) {
			return true;
		}
	}

	public static boolean isTokenValid(String token, UserDetails userDetails) {
		String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	
	
}
