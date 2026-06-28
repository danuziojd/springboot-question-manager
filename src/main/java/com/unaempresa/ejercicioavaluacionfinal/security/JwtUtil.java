package com.unaempresa.ejercicioavaluacionfinal.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String jwtSecret;

	private final long expirationMs = 1000L * 60 * 60 * 2;

	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}

	public String generarToken(String username) {
		Date ahora = new Date();
		Date expiracion = new Date(ahora.getTime() + expirationMs);

		return Jwts.builder()
				.subject(username)
				.issuedAt(ahora)
				.expiration(expiracion)
				.signWith(getSecretKey())
				.compact();
	}

	public String extraerUsername(String token) {
		return Jwts.parser()
				.verifyWith(getSecretKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}

	public boolean esTokenValido(String token) {
		try {
			Jwts.parser()
					.verifyWith(getSecretKey())
					.build()
					.parseSignedClaims(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}