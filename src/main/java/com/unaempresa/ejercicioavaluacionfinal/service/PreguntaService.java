package com.unaempresa.ejercicioavaluacionfinal.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unaempresa.ejercicioavaluacionfinal.entity.Pregunta;
import com.unaempresa.ejercicioavaluacionfinal.entity.PreguntaSeleccionMultiple;
import com.unaempresa.ejercicioavaluacionfinal.entity.PreguntaSeleccionUnica;
import com.unaempresa.ejercicioavaluacionfinal.entity.PreguntaVerdadeiroFalso;
import com.unaempresa.ejercicioavaluacionfinal.exception.PreguntaNoEncontradaException;
import com.unaempresa.ejercicioavaluacionfinal.repository.PreguntaRepository;

@Service
public class PreguntaService {
    private final PreguntaRepository preguntaRepository;

    public PreguntaService(PreguntaRepository preguntaRepository) {
        this.preguntaRepository = preguntaRepository;
    }

    public Page<Pregunta> listarTodas(Pageable pageable) {
        return preguntaRepository.findAll(pageable);
    }

    public Page<Pregunta> listarPorTematica(Long tematicaId, Pageable pageable) {
        return preguntaRepository.findByTematicaId(tematicaId, pageable);
    }

    public Page<Pregunta> listarPorTipo(Class<? extends Pregunta> tipo, Pageable pageable) {
        return preguntaRepository.findByTipo(tipo, pageable);
    }

    public Page<Pregunta> listarPorTematicaYTipo(Long tematicaId, Class<? extends Pregunta> tipo, Pageable pageable) {
        return preguntaRepository.findByTematicaIdAndTipo(tematicaId, tipo, pageable);
    }

    public Pregunta obtenerPorId(Long id) {
        return preguntaRepository.findById(id)
            .orElseThrow(() -> new PreguntaNoEncontradaException("Pregunta no encontrada: " + id));
    }

    @Transactional
    public Pregunta guardar(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    @Transactional
    public Pregunta actualizar(Long id, Pregunta datos) {
        Pregunta existente = obtenerPorId(id);
        existente.setEnunciado(datos.getEnunciado());
        existente.setDificultad(datos.getDificultad());
        existente.setTematica(datos.getTematica());

        if (existente instanceof PreguntaVerdadeiroFalso vf && datos instanceof PreguntaVerdadeiroFalso vfDatos) {
            vf.setRespuestaCorrecta(vfDatos.getRespuestaCorrecta());
        } else if (existente instanceof PreguntaSeleccionUnica su && datos instanceof PreguntaSeleccionUnica suDatos) {
            su.setOpciones(suDatos.getOpciones());
            su.setRespuestaCorrecta(suDatos.getRespuestaCorrecta());
        } else if (existente instanceof PreguntaSeleccionMultiple sm && datos instanceof PreguntaSeleccionMultiple smDatos) {
            sm.setOpciones(smDatos.getOpciones());
            sm.setRespuestasCorrectas(smDatos.getRespuestasCorrectas());
        }

        return preguntaRepository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!preguntaRepository.existsById(id)) {
            throw new PreguntaNoEncontradaException("Pregunta no encontrada: " + id);
        }
        preguntaRepository.deleteById(id);
    }
}
