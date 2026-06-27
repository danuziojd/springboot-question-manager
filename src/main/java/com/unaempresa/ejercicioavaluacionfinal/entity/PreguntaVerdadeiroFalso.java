package com.unaempresa.ejercicioavaluacionfinal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "preguntas_verdadeiro_falso")
public class PreguntaVerdadeiroFalso extends Pregunta {

	private Boolean respuestaCorrecta;
	public PreguntaVerdadeiroFalso() {}
	public PreguntaVerdadeiroFalso(String enunciado, Integer dificultad, Tematica tematica, Boolean respuestaCorrecta) {
		super(enunciado, dificultad, tematica);
		this.respuestaCorrecta = respuestaCorrecta;
	}

	public Boolean getRespuestaCorrecta() {return respuestaCorrecta;}
	public void setRespuestaCorrecta(Boolean respuestaCorrecta) {this.respuestaCorrecta = respuestaCorrecta;}
}
