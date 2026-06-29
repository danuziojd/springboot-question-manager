package com.unaempresa.ejercicioavaluacionfinal.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
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

import com.unaempresa.ejercicioavaluacionfinal.entity.Pregunta;
import com.unaempresa.ejercicioavaluacionfinal.entity.PreguntaVerdadeiroFalso;
import com.unaempresa.ejercicioavaluacionfinal.entity.Tematica;
import com.unaempresa.ejercicioavaluacionfinal.exception.PreguntaNoEncontradaException;
import com.unaempresa.ejercicioavaluacionfinal.repository.PreguntaRepository;

@ExtendWith(MockitoExtension.class)
class PreguntaServiceTest {

	@Mock
	private PreguntaRepository preguntaRepository;

	@InjectMocks
	private PreguntaService preguntaService;

	private Tematica tematica;
	private PreguntaVerdadeiroFalso preguntaExistente;

	@BeforeEach
	void setUp() {
		tematica = new Tematica("Matematicas");
		tematica.setId(1L);

		preguntaExistente = new PreguntaVerdadeiroFalso("¿2+2=4?", 1, tematica, true);
		preguntaExistente.setId(10L);
	}

	@Test
	void obtenerPorId_deberiaDevolverLaPregunta_cuandoExiste() {
		when(preguntaRepository.findById(10L)).thenReturn(Optional.of(preguntaExistente));

		Pregunta resultado = preguntaService.obtenerPorId(10L);

		assertThat(resultado).isNotNull();
		assertThat(resultado.getId()).isEqualTo(10L);
		assertThat(resultado.getEnunciado()).isEqualTo("¿2+2=4?");
	}

	@Test
	void obtenerPorId_deberiaLanzarExcepcion_cuandoNoExiste() {
		when(preguntaRepository.findById(999L)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> preguntaService.obtenerPorId(999L))
				.isInstanceOf(PreguntaNoEncontradaException.class)
				.hasMessageContaining("999");
	}

	@Test
	void guardar_deberiaGuardarLaPreguntaCorrectamente() {
		PreguntaVerdadeiroFalso preguntaNueva = new PreguntaVerdadeiroFalso("¿El cielo es azul?", 1, tematica, true);
		when(preguntaRepository.save(preguntaNueva)).thenReturn(preguntaNueva);

		Pregunta resultado = preguntaService.guardar(preguntaNueva);

		assertThat(resultado).isNotNull();
		assertThat(resultado.getEnunciado()).isEqualTo("¿El cielo es azul?");
		verify(preguntaRepository, times(1)).save(preguntaNueva);
	}

	@Test
	void actualizar_deberiaActualizarLosCamposCorrectamente() {
		PreguntaVerdadeiroFalso datosNuevos = new PreguntaVerdadeiroFalso("Pregunta actualizada", 3, tematica, false);

		when(preguntaRepository.findById(10L)).thenReturn(Optional.of(preguntaExistente));
		when(preguntaRepository.save(any(Pregunta.class))).thenAnswer(invocacion -> invocacion.getArgument(0));

		Pregunta resultado = preguntaService.actualizar(10L, datosNuevos);

		assertThat(resultado.getEnunciado()).isEqualTo("Pregunta actualizada");
		assertThat(resultado.getDificultad()).isEqualTo(3);
	}

	@Test
	void actualizar_deberiaLanzarExcepcion_cuandoLaPreguntaNoExiste() {
		PreguntaVerdadeiroFalso datosNuevos = new PreguntaVerdadeiroFalso("Pregunta actualizada", 3, tematica, false);

		when(preguntaRepository.findById(999L)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> preguntaService.actualizar(999L, datosNuevos))
				.isInstanceOf(PreguntaNoEncontradaException.class);
	}

	@Test
	void eliminar_deberiaEliminarLaPregunta_cuandoExiste() {
		when(preguntaRepository.existsById(10L)).thenReturn(true);

		preguntaService.eliminar(10L);

		verify(preguntaRepository, times(1)).deleteById(10L);
	}

	@Test
	void eliminar_deberiaLanzarExcepcion_cuandoNoExiste() {
		when(preguntaRepository.existsById(999L)).thenReturn(false);

		assertThatThrownBy(() -> preguntaService.eliminar(999L))
				.isInstanceOf(PreguntaNoEncontradaException.class);

		verify(preguntaRepository, never()).deleteById(999L);
	}
}