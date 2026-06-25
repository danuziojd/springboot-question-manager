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

import com.unaempresa.ejercicioavaluacionfinal.entity.Tematica;
import com.unaempresa.ejercicioavaluacionfinal.service.TematicaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tematicas")
public class TematicaController {

    private final TematicaService tematicaService;

    public TematicaController(TematicaService tematicaService) {
        this.tematicaService = tematicaService;
    }

    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "5") int size,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Tematica> pagina = tematicaService.listarPaginadas(pageRequest);
        model.addAttribute("pagina", pagina);
        model.addAttribute("tematicas", pagina.getContent());
        return "tematicas/listar";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("tematica", new Tematica());
        return "tematicas/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Tematica tematica = tematicaService.obtenerPorId(id);
        model.addAttribute("tematica", tematica);
        return "tematicas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Tematica tematica,
                          BindingResult result,
                          RedirectAttributes redirect,
                          Model model) {
        if (result.hasErrors()) {
            return "tematicas/formulario";
        }
        tematicaService.guardar(tematica);
        redirect.addFlashAttribute("mensaje", "Tematica guardada correctamente");
        return "redirect:/tematicas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            tematicaService.eliminar(id);
            redirect.addFlashAttribute("mensaje", "Tematica eliminada correctamente");
        } catch (RuntimeException e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/tematicas";
    }
}