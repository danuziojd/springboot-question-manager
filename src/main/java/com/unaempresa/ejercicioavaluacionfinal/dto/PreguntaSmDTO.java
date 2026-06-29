package com.unaempresa.ejercicioavaluacionfinal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreguntaSmDTO extends PreguntaDTO {
	private String opciones;
	private String respuestasCorrectas;
}
