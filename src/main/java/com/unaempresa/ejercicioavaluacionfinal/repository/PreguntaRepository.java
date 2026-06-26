package com.unaempresa.ejercicioavaluacionfinal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.unaempresa.ejercicioavaluacionfinal.entity.Pregunta;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long>{

    Page<Pregunta> findByTematicaId(Long tematicaId, Pageable pageable);

    @Query("SELECT p FROM Pregunta p WHERE TYPE(p) = :tipo")
    Page<Pregunta> findByTipo(@Param("tipo") Class<? extends Pregunta> tipo, Pageable pageable);

    @Query("SELECT p FROM Pregunta p WHERE p.tematica.id = :tematicaId AND TYPE(p) = :tipo")
    Page<Pregunta> findByTematicaIdAndTipo(@Param("tematicaId") Long tematicaId,
                                           @Param("tipo") Class<? extends Pregunta> tipo,
                                           Pageable pageable);
}