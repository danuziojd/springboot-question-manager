package com.unaempresa.ejercicioavaluacionfinal.mapper;

import com.unaempresa.ejercicioavaluacionfinal.dto.PreguntaDTO;
import com.unaempresa.ejercicioavaluacionfinal.dto.PreguntaRequestDTO;
import com.unaempresa.ejercicioavaluacionfinal.dto.PreguntaSmDTO;
import com.unaempresa.ejercicioavaluacionfinal.dto.PreguntaSuDTO;
import com.unaempresa.ejercicioavaluacionfinal.dto.PreguntaVfDTO;
import com.unaempresa.ejercicioavaluacionfinal.entity.Pregunta;
import com.unaempresa.ejercicioavaluacionfinal.entity.PreguntaSeleccionMultiple;
import com.unaempresa.ejercicioavaluacionfinal.entity.PreguntaSeleccionUnica;
import com.unaempresa.ejercicioavaluacionfinal.entity.PreguntaVerdadeiroFalso;
import com.unaempresa.ejercicioavaluacionfinal.entity.Tematica;

public class PreguntaMapper {
	public static PreguntaDTO toDTO(Pregunta p) {
		if (p instanceof PreguntaVerdadeiroFalso vf) {
			PreguntaVfDTO dto = new PreguntaVfDTO();
			dto.setRespuestaCorrecta(vf.getRespuestaCorrecta());
			dto.setTipo("VF");
			fillBaseFields(dto, p);
			return dto;
		}

		if (p instanceof PreguntaSeleccionUnica su) {
			PreguntaSuDTO dto = new PreguntaSuDTO();
			dto.setOpciones(su.getOpciones());
			dto.setRespuestaCorrecta(su.getRespuestaCorrecta());
			dto.setTipo("SU");
			fillBaseFields(dto, p);
			return dto;
		}

		if (p instanceof PreguntaSeleccionMultiple sm) {
			PreguntaSmDTO dto = new PreguntaSmDTO();
			dto.setOpciones(sm.getOpciones());
			dto.setRespuestasCorrectas(sm.getRespuestasCorrectas());
			dto.setTipo("SM");
			fillBaseFields(dto, p);
			return dto;
		}
		return null;
	}

	public static Pregunta toEntity(PreguntaRequestDTO dto, Tematica tematica) {
		return switch (dto.getTipo()) {
			case "VF" -> {
				PreguntaVerdadeiroFalso vf = new PreguntaVerdadeiroFalso();
				vf.setRespuestaCorrecta(dto.getRespuestaCorrectaVf());
				fillBaseEntityFields(vf, dto, tematica);
				yield vf;
			}
			case "SU" -> {
				PreguntaSeleccionUnica su = new PreguntaSeleccionUnica();
				su.setOpciones(dto.getOpciones());
				su.setRespuestaCorrecta(dto.getRespuestaCorrecta());
				fillBaseEntityFields(su, dto, tematica);
				yield su;
			}
			case "SM" -> {
				PreguntaSeleccionMultiple sm = new PreguntaSeleccionMultiple();
				sm.setOpciones(dto.getOpciones());
				sm.setRespuestasCorrectas(dto.getRespuestaCorrecta());
				fillBaseEntityFields(sm, dto, tematica);
				yield sm;
			}
			default -> throw new IllegalArgumentException("Tipo de pregunta no válido: " + dto.getTipo());
		};
	}

	private static void fillBaseFields(PreguntaDTO dto, Pregunta p) {
		dto.setId(p.getId());
		dto.setEnunciado(p.getEnunciado());
		dto.setDificultad(p.getDificultad());
		dto.setTematicaId(p.getTematica().getId());
	}

	private static void fillBaseEntityFields(Pregunta p, PreguntaRequestDTO dto, Tematica tematica) {
		p.setEnunciado(dto.getEnunciado());
		p.setDificultad(dto.getDificultad());
		p.setTematica(tematica);
	}
}
