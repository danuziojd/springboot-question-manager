package com.unaempresa.ejercicioavaluacionfinal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "preguntas")
public class Pregunta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "El enunciado es obligatorio")
	@Column(nullable = false, length = 1000)
	private String enunciado;
	
	@NotNull(message = "La dificultad es obligatoria")
	private Integer dificultad;
	
	@ManyToOne
	@JoinColumn(name = "tematica_id", nullable = false)
	@NotNull(message = "La temática es obligatoria")
	private Tematica tematica;
	
	public Pregunta() {}

	public Pregunta(String enunciado,Integer dificultad,Tematica tematica) {
		this.enunciado = enunciado;
		this.dificultad = dificultad;
		this.tematica = tematica;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public Integer getDificultad() {
		return dificultad;
	}

	public void setDificultad(Integer dificultad) {
		this.dificultad = dificultad;
	}

	public Tematica getTematica() {
		return tematica;
	}

	public void setTematica(Tematica tematica) {
		this.tematica = tematica;
	}
}