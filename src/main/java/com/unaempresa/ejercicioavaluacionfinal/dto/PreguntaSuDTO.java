package com.unaempresa.ejercicioavaluacionfinal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreguntaSuDTO extends PreguntaDTO {
	private String opciones;
	private String respuestaCorrecta;
}
