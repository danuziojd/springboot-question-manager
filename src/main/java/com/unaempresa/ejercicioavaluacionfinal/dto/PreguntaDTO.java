package com.unaempresa.ejercicioavaluacionfinal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreguntaDTO {
	private Long id;
	private String enunciado;
	private Integer dificultad;
	private Long tematicaId;
	private String tipo;
}
