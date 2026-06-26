package com.unaempresa.ejercicioavaluacionfinal.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unaempresa.ejercicioavaluacionfinal.entity.Pregunta;
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
    public void eliminar(Long id) {
        if (!preguntaRepository.existsById(id)) {
            throw new PreguntaNoEncontradaException("Pregunta no encontrada: " + id);
        }
        preguntaRepository.deleteById(id);
    }
}
