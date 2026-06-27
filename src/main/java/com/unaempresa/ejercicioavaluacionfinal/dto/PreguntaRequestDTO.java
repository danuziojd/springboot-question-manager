package com.unaempresa.ejercicioavaluacionfinal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreguntaRequestDTO {
    private String enunciado;
    private Integer dificultad;
    private Long tematicaId;
    private String tipo;
    private String opciones;
    private String respuestaCorrecta;
    private Boolean respuestaCorrectaVf;
}