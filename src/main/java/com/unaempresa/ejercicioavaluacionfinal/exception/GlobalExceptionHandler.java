package com.unaempresa.ejercicioavaluacionfinal.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(PreguntaNoEncontradaException.class)
	public String manejarPreguntaNoEncontrada(PreguntaNoEncontradaException ex, Model model) {
		model.addAttribute("mensaje", ex.getMessage());
		return "error/404";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String manejarNoHandlerFound(NoHandlerFoundException ex, Model model) {
		model.addAttribute("mensaje", "La página que buscas no existe.");
		return "error/404";
	}
	
	@ExceptionHandler(Exception.class)
	public String manejarErrorGeneral(Exception ex, Model model) {
		model.addAttribute("mensaje", "Se ha producido un error inesperado.");
		return "error/500";
	}
}
