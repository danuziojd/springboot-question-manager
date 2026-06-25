package com.unaempresa.ejercicioavaluacionfinal.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unaempresa.ejercicioavaluacionfinal.entity.Pregunta;
import com.unaempresa.ejercicioavaluacionfinal.service.PreguntaService;
import com.unaempresa.ejercicioavaluacionfinal.service.TematicaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/preguntas")
public class PreguntaController {

    private final PreguntaService preguntaService;
    private final TematicaService tematicaService;

    public PreguntaController(PreguntaService preguntaService, TematicaService tematicaService) {
        this.preguntaService = preguntaService;
        this.tematicaService = tematicaService;
    }

    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "5") int size,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Pregunta> pagina = preguntaService.listarTodas(pageRequest);
        model.addAttribute("pagina", pagina);
        model.addAttribute("preguntas", pagina.getContent());
        return "preguntas/listar";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("pregunta", new Pregunta());
        model.addAttribute("tematicas", tematicaService.listarTodas());
        return "preguntas/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Pregunta pregunta = preguntaService.obtenerPorId(id);
        model.addAttribute("pregunta", pregunta);
        model.addAttribute("tematicas", tematicaService.listarTodas());
        return "preguntas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Pregunta pregunta,
                          BindingResult result,
                          RedirectAttributes redirect,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tematicas", tematicaService.listarTodas());
            return "preguntas/formulario";
        }
        preguntaService.guardar(pregunta);
        redirect.addFlashAttribute("mensaje", "Pregunta guardada correctamente");
        return "redirect:/preguntas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            preguntaService.eliminar(id);
            redirect.addFlashAttribute("mensaje", "Pregunta eliminada correctamente");
        } catch (RuntimeException e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/preguntas";
    }
}