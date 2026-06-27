package com.unaempresa.ejercicioavaluacionfinal.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unaempresa.ejercicioavaluacionfinal.dto.PreguntaDTO;
import com.unaempresa.ejercicioavaluacionfinal.dto.PreguntaRequestDTO;
import com.unaempresa.ejercicioavaluacionfinal.mapper.PreguntaMapper;
import com.unaempresa.ejercicioavaluacionfinal.service.PreguntaService;
import com.unaempresa.ejercicioavaluacionfinal.service.TematicaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/preguntas")
public class PreguntaRestController {
	private final PreguntaService service;
	private final TematicaService tematicaService;

	public PreguntaRestController(PreguntaService service, TematicaService tematicaService) {
		this.service = service;
		this.tematicaService = tematicaService;
	}

	@GetMapping
	public Page<PreguntaDTO> listar(@RequestParam(defaultValue = "0") int page,
									@RequestParam(defaultValue = "5") int size) {
		return service.listarTodas(PageRequest.of(page, size)).map(PreguntaMapper::toDTO);
	}

	@GetMapping("/{id}")
	public PreguntaDTO obtener(@PathVariable Long id) {
		return PreguntaMapper.toDTO(service.obtenerPorId(id));
	}

	@PostMapping
	public ResponseEntity<PreguntaDTO> crear(@RequestBody @Valid PreguntaRequestDTO dto) {
		var tematica = tematicaService.obtenerPorId(dto.getTematicaId());
		var pregunta = PreguntaMapper.toEntity(dto, tematica);
		var saved = service.guardar(pregunta);
		return ResponseEntity.status(HttpStatus.CREATED).body(PreguntaMapper.toDTO(saved));
	}

	@PutMapping("/{id}")
	public PreguntaDTO actualizar(@PathVariable Long id, @RequestBody @Valid PreguntaRequestDTO dto) {
		var tematica = tematicaService.obtenerPorId(dto.getTematicaId());
		var datos = PreguntaMapper.toEntity(dto, tematica);
		return PreguntaMapper.toDTO(service.actualizar(id, datos));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
