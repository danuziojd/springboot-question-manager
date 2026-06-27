package com.unaempresa.ejercicioavaluacionfinal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "preguntas_seleccion_unica")
public class PreguntaSeleccionUnica extends Pregunta {
	private String opciones;
	private String respuestaCorrecta;

	public PreguntaSeleccionUnica() {}

	public PreguntaSeleccionUnica(String enunciado, Integer dificultad, Tematica tematica, String opciones, String respuestaCorrecta) {
		super(enunciado, dificultad, tematica);
		this.opciones = opciones;
		this.respuestaCorrecta = respuestaCorrecta;
	}

	public String getOpciones() {return opciones;}
	public void setOpciones(String opciones) {this.opciones = opciones;}

	public String getRespuestaCorrecta() {return respuestaCorrecta;}
	public void setRespuestaCorrecta(String respuestaCorrecta) {this.respuestaCorrecta = respuestaCorrecta;}
}
