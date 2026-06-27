package com.unaempresa.ejercicioavaluacionfinal.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import com.unaempresa.ejercicioavaluacionfinal.entity.Pregunta;
import com.unaempresa.ejercicioavaluacionfinal.entity.PreguntaSeleccionMultiple;
import com.unaempresa.ejercicioavaluacionfinal.entity.PreguntaSeleccionUnica;
import com.unaempresa.ejercicioavaluacionfinal.entity.PreguntaVerdadeiroFalso;
import com.unaempresa.ejercicioavaluacionfinal.mapper.PreguntaMapper;
import com.unaempresa.ejercicioavaluacionfinal.service.PreguntaService;
import com.unaempresa.ejercicioavaluacionfinal.service.TematicaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/preguntas")
@Tag(name = "Preguntas", description = "Operaciones CRUD sobre preguntas tipo test")
public class PreguntaRestController {
	private final PreguntaService service;
	private final TematicaService tematicaService;

	public PreguntaRestController(PreguntaService service, TematicaService tematicaService) {
		this.service = service;
		this.tematicaService = tematicaService;
	}
	
	@GetMapping
	@Operation(summary = "Listar preguntas", description = "Devuelve un listado paginado de preguntas, "
			+ "con filtro opcional por temática y/o tipo (VF, SU, SM).")
	public Page<PreguntaDTO> listar(
			@Parameter(description = "Número de página (empieza en 0)") @RequestParam(defaultValue = "0") int page,
			@Parameter(description = "Cantidad de elementos por página") @RequestParam(defaultValue = "5") int size,
			@Parameter(description = "ID de la temática para filtrar (opcional)") @RequestParam(required = false) Long tematicaId,
			@Parameter(description = "Tipo de pregunta: VF, SU o SM (opcional)") @RequestParam(required = false) String tipo) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").ascending());

		Class<? extends Pregunta> tipoClass = null;
		if (tipo != null && !tipo.isEmpty()) {
			tipoClass = switch (tipo) {
				case "VF" -> PreguntaVerdadeiroFalso.class;
				case "SU" -> PreguntaSeleccionUnica.class;
				case "SM" -> PreguntaSeleccionMultiple.class;
				default -> null;
			};
		}

		Page<Pregunta> pagina;
		if (tematicaId != null && tipoClass != null) {
			pagina = service.listarPorTematicaYTipo(tematicaId, tipoClass, pageRequest);
		} else if (tematicaId != null) {
			pagina = service.listarPorTematica(tematicaId, pageRequest);
		} else if (tipoClass != null) {
			pagina = service.listarPorTipo(tipoClass, pageRequest);
		} else {
			pagina = service.listarTodas(pageRequest);
		}

		return pagina.map(PreguntaMapper::toDTO);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Obtener una pregunta por ID", description = "Devuelve los datos de una pregunta específica. "
			+ "Lanza 404 si no existe.")
	public PreguntaDTO obtener(@Parameter(description = "ID de la pregunta") @PathVariable Long id) {
		return PreguntaMapper.toDTO(service.obtenerPorId(id));
	}

	@PostMapping
	@Operation(summary = "Crear una nueva pregunta", description = "Crea una pregunta del tipo indicado en el campo "
			+ "'tipo' (VF, SU o SM) del cuerpo de la petición.")
	public ResponseEntity<PreguntaDTO> crear(@RequestBody @Valid PreguntaRequestDTO dto) {
		var tematica = tematicaService.obtenerPorId(dto.getTematicaId());
		var pregunta = PreguntaMapper.toEntity(dto, tematica);
		var saved = service.guardar(pregunta);
		return ResponseEntity.status(HttpStatus.CREATED).body(PreguntaMapper.toDTO(saved));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualizar una pregunta existente", description = "Reemplaza los datos de la pregunta indicada por ID.")
	public PreguntaDTO actualizar(@Parameter(description = "ID de la pregunta a actualizar") @PathVariable Long id,
			@RequestBody @Valid PreguntaRequestDTO dto) {
		var tematica = tematicaService.obtenerPorId(dto.getTematicaId());
		var datos = PreguntaMapper.toEntity(dto, tematica);
		return PreguntaMapper.toDTO(service.actualizar(id, datos));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar una pregunta", description = "Elimina la pregunta indicada por ID. Devuelve 204 si se elimina correctamente.")
	public ResponseEntity<Void> eliminar(@Parameter(description = "ID de la pregunta a eliminar") @PathVariable Long id) {
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}