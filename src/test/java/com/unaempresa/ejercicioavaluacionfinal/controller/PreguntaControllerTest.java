package com.unaempresa.ejercicioavaluacionfinal.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.unaempresa.ejercicioavaluacionfinal.entity.Pregunta;
import com.unaempresa.ejercicioavaluacionfinal.entity.PreguntaVerdadeiroFalso;
import com.unaempresa.ejercicioavaluacionfinal.entity.Tematica;
import com.unaempresa.ejercicioavaluacionfinal.exception.PreguntaNoEncontradaException;
import com.unaempresa.ejercicioavaluacionfinal.service.PreguntaService;
import com.unaempresa.ejercicioavaluacionfinal.service.TematicaService;

@WebMvcTest(value = PreguntaRestController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.unaempresa\\.ejercicioavaluacionfinal\\.security\\..*")
})
@AutoConfigureMockMvc(addFilters = false)
class PreguntaRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private PreguntaService preguntaService;

	@MockitoBean
	private TematicaService tematicaService;

	@Test
	void listar_deberiaDevolver200YLaListaDePreguntas() throws Exception {
		Tematica tematica = new Tematica("Matematicas");
		tematica.setId(1L);

		PreguntaVerdadeiroFalso pregunta = new PreguntaVerdadeiroFalso("¿2+2=4?", 1, tematica, true);
		pregunta.setId(10L);

		var pagina = new PageImpl<Pregunta>(List.of(pregunta), PageRequest.of(0, 5), 1);

		when(preguntaService.listarTodas(any())).thenReturn(pagina);

		mockMvc.perform(get("/api/preguntas"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content[0].id").value(10))
				.andExpect(jsonPath("$.content[0].enunciado").value("¿2+2=4?"))
				.andExpect(jsonPath("$.content[0].tipo").value("VF"));
	}

	@Test
	void obtener_deberiaDevolver200_cuandoLaPreguntaExiste() throws Exception {
		Tematica tematica = new Tematica("Matematicas");
		tematica.setId(1L);

		PreguntaVerdadeiroFalso pregunta = new PreguntaVerdadeiroFalso("¿2+2=4?", 1, tematica, true);
		pregunta.setId(10L);

		when(preguntaService.obtenerPorId(10L)).thenReturn(pregunta);

		mockMvc.perform(get("/api/preguntas/10"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(10))
				.andExpect(jsonPath("$.enunciado").value("¿2+2=4?"));
	}

	@Test
	void obtener_deberiaDevolver404_cuandoLaPreguntaNoExiste() throws Exception {
		when(preguntaService.obtenerPorId(999L))
				.thenThrow(new PreguntaNoEncontradaException("Pregunta no encontrada: 999"));

		mockMvc.perform(get("/api/preguntas/999"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.mensaje").value("Pregunta no encontrada: 999"));
	}

	@Test
	void eliminar_deberiaDevolver204_cuandoSeEliminaCorrectamente() throws Exception {
		mockMvc.perform(delete("/api/preguntas/10"))
				.andExpect(status().isNoContent());
	}
}