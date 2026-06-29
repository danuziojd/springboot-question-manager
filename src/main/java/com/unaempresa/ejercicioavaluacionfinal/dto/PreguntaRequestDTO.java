package com.unaempresa.ejercicioavaluacionfinal.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreguntaRequestDTO {

    @NotBlank(message = "El enunciado es obligatorio")
    @Size(max = 1000, message = "El enunciado no puede superar los 1000 caracteres")
    private String enunciado;

    @NotNull(message = "La dificultad es obligatoria")
    @Min(value = 1, message = "La dificultad minima es 1")
    @Max(value = 5, message = "La dificultad maxima es 5")
    private Integer dificultad;

    @NotNull(message = "La tematica es obligatoria")
    private Long tematicaId;

    @NotBlank(message = "El tipo de pregunta es obligatorio")
    private String tipo;

    private String opciones;
    private String respuestaCorrecta;
    private String respuestasCorrectas;
    private Boolean respuestaCorrectaVf;
}