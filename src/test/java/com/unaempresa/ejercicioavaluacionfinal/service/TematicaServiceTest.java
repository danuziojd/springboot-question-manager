package com.unaempresa.ejercicioavaluacionfinal.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.unaempresa.ejercicioavaluacionfinal.entity.Tematica;
import com.unaempresa.ejercicioavaluacionfinal.exception.TematicaNoEncontradaException;
import com.unaempresa.ejercicioavaluacionfinal.repository.TematicaRepository;

@ExtendWith(MockitoExtension.class)
class TematicaServiceTest {

	@Mock
	private TematicaRepository tematicaRepository;

	@InjectMocks
	private TematicaService tematicaService;

	private Tematica tematicaExistente;

	@BeforeEach
	void setUp() {
		tematicaExistente = new Tematica("Matematicas");
		tematicaExistente.setId(1L);
	}

	@Test
	void obtenerPorId_deberiaDevolverLaTematica_cuandoExiste() {
		when(tematicaRepository.findById(1L)).thenReturn(Optional.of(tematicaExistente));

		Tematica resultado = tematicaService.obtenerPorId(1L);

		assertThat(resultado).isNotNull();
		assertThat(resultado.getId()).isEqualTo(1L);
		assertThat(resultado.getNombre()).isEqualTo("Matematicas");
	}

	@Test
	void obtenerPorId_deberiaLanzarExcepcion_cuandoNoExiste() {
		when(tematicaRepository.findById(999L)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> tematicaService.obtenerPorId(999L))
				.isInstanceOf(TematicaNoEncontradaException.class)
				.hasMessageContaining("999");
	}

	@Test
	void guardar_deberiaGuardarLaTematicaCorrectamente() {
		Tematica tematicaNueva = new Tematica("Historia");
		when(tematicaRepository.save(tematicaNueva)).thenReturn(tematicaNueva);

		Tematica resultado = tematicaService.guardar(tematicaNueva);

		assertThat(resultado).isNotNull();
		assertThat(resultado.getNombre()).isEqualTo("Historia");
		verify(tematicaRepository, times(1)).save(tematicaNueva);
	}

	@Test
	void eliminar_deberiaEliminarLaTematica_cuandoExiste() {
		when(tematicaRepository.existsById(1L)).thenReturn(true);

		tematicaService.eliminar(1L);

		verify(tematicaRepository, times(1)).deleteById(1L);
	}

	@Test
	void eliminar_deberiaLanzarExcepcion_cuandoNoExiste() {
		when(tematicaRepository.existsById(999L)).thenReturn(false);

		assertThatThrownBy(() -> tematicaService.eliminar(999L))
				.isInstanceOf(TematicaNoEncontradaException.class);

		verify(tematicaRepository, never()).deleteById(999L);
	}
}