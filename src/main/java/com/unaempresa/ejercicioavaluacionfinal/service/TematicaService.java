package com.unaempresa.ejercicioavaluacionfinal.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unaempresa.ejercicioavaluacionfinal.entity.Tematica;
import com.unaempresa.ejercicioavaluacionfinal.repository.TematicaRepository;

@Service
public class TematicaService {
    private final TematicaRepository tematicaRepository;

    public TematicaService(TematicaRepository tematicaRepository) {
        this.tematicaRepository = tematicaRepository;
    }

    public List<Tematica> listarTodas() {
        return tematicaRepository.findAll();
    }

    public Page<Tematica> listarPaginadas(Pageable pageable) {
        return tematicaRepository.findAll(pageable);
    }

    public Tematica obtenerPorId(Long id) {
        return tematicaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tematica no encontrada: " + id));
    }

    @Transactional
    public Tematica guardar(Tematica tematica) {
        return tematicaRepository.save(tematica);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!tematicaRepository.existsById(id)) {
            throw new RuntimeException("Tematica no encontrada: " + id);
        }
        tematicaRepository.deleteById(id);
    }
}