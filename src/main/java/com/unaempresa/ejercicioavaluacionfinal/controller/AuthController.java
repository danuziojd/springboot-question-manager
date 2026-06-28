package com.unaempresa.ejercicioavaluacionfinal.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unaempresa.ejercicioavaluacionfinal.dto.LoginRequestDTO;
import com.unaempresa.ejercicioavaluacionfinal.dto.LoginResponseDTO;
import com.unaempresa.ejercicioavaluacionfinal.security.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Login y generación de tokens JWT")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;

	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	@Operation(summary = "Iniciar sesión", description = "Valida usuario y contraseña, y devuelve un token JWT "
			+ "que debe enviarse en el header 'Authorization: Bearer <token>' en las siguientes peticiones a la API.")
	public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO dto) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

		String token = jwtUtil.generarToken(dto.getUsername());
		return new LoginResponseDTO(token);
	}
}