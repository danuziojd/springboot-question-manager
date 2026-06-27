package com.unaempresa.ejercicioavaluacionfinal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "preguntas_seleccion_multiple")
public class PreguntaSeleccionMultiple extends Pregunta {
	private String opciones;
	private String respuestasCorrectas;

	public PreguntaSeleccionMultiple() {}

	public PreguntaSeleccionMultiple(String enunciado, Integer dificultad, Tematica tematica, String opciones, String respuestasCorrectas) {
		super(enunciado, dificultad, tematica);
		this.opciones = opciones;
		this.respuestasCorrectas = respuestasCorrectas;
	}

	public String getOpciones() {return opciones;}
	public void setOpciones(String opciones) {this.opciones = opciones;}

	public String getRespuestasCorrectas() {return respuestasCorrectas;}
	public void setRespuestasCorrectas(String respuestasCorrectas) {this.respuestasCorrectas = respuestasCorrectas;}
}
