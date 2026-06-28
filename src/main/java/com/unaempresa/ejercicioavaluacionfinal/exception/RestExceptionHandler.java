package com.unaempresa.ejercicioavaluacionfinal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.unaempresa.ejercicioavaluacionfinal.controller.AuthController;
import com.unaempresa.ejercicioavaluacionfinal.controller.PreguntaRestController;
import com.unaempresa.ejercicioavaluacionfinal.dto.ErrorResponseDTO;

@RestControllerAdvice(assignableTypes = { PreguntaRestController.class, AuthController.class })
public class RestExceptionHandler {

	@ExceptionHandler(PreguntaNoEncontradaException.class)
	public ResponseEntity<ErrorResponseDTO> manejarPreguntaNoEncontrada(PreguntaNoEncontradaException ex) {
		ErrorResponseDTO body = new ErrorResponseDTO(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}

	@ExceptionHandler(TematicaNoEncontradaException.class)
	public ResponseEntity<ErrorResponseDTO> manejarTematicaNoEncontrada(TematicaNoEncontradaException ex) {
		ErrorResponseDTO body = new ErrorResponseDTO(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorResponseDTO> manejarAutenticacionInvalida(AuthenticationException ex) {
		ErrorResponseDTO body = new ErrorResponseDTO("Usuario o contraseña incorrectos.");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponseDTO> manejarArgumentoInvalido(IllegalArgumentException ex) {
		ErrorResponseDTO body = new ErrorResponseDTO(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> manejarErrorGeneral(Exception ex) {
		ErrorResponseDTO body = new ErrorResponseDTO("Se ha producido un error inesperado en la API.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	}
}