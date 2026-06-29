package com.unaempresa.ejercicioavaluacionfinal.controller;

import java.beans.PropertyEditorSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unaempresa.ejercicioavaluacionfinal.entity.Pregunta;
import com.unaempresa.ejercicioavaluacionfinal.entity.PreguntaSeleccionMultiple;
import com.unaempresa.ejercicioavaluacionfinal.entity.PreguntaSeleccionUnica;
import com.unaempresa.ejercicioavaluacionfinal.entity.PreguntaVerdadeiroFalso;
import com.unaempresa.ejercicioavaluacionfinal.entity.Tematica;
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Tematica.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text != null && !text.isEmpty()) {
                    setValue(tematicaService.obtenerPorId(Long.parseLong(text)));
                } else {
                    setValue(null);
                }
            }
        });
    }

    @ModelAttribute
    public void initPregunta(@RequestParam(required = false) Long id,
                              @RequestParam(required = false, defaultValue = "VF") String tipo,
                              Model model) {
        if (!model.containsAttribute("pregunta")) {
            Pregunta pregunta;
            if (id != null) {
                pregunta = preguntaService.obtenerPorId(id);
            } else {
                pregunta = switch (tipo) {
                    case "VF" -> new PreguntaVerdadeiroFalso();
                    case "SU" -> new PreguntaSeleccionUnica();
                    case "SM" -> new PreguntaSeleccionMultiple();
                    default -> new PreguntaVerdadeiroFalso();
                };
            }
            model.addAttribute("pregunta", pregunta);
        }
    }

    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "5") int size,
                         @RequestParam(required = false) Long tematicaId,
                         @RequestParam(required = false) String tipo,
                         Model model) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Pregunta> pagina;

        Class<? extends Pregunta> tipoClass = null;
        if (tipo != null && !tipo.isEmpty()) {
            tipoClass = switch (tipo) {
                case "VF" -> PreguntaVerdadeiroFalso.class;
                case "SU" -> PreguntaSeleccionUnica.class;
                case "SM" -> PreguntaSeleccionMultiple.class;
                default -> null;
            };
        }

        if (tematicaId != null && tipoClass != null) {
            pagina = preguntaService.listarPorTematicaYTipo(tematicaId, tipoClass, pageRequest);
        } else if (tematicaId != null) {
            pagina = preguntaService.listarPorTematica(tematicaId, pageRequest);
        } else if (tipoClass != null) {
            pagina = preguntaService.listarPorTipo(tipoClass, pageRequest);
        } else {
            pagina = preguntaService.listarTodas(pageRequest);
        }

        model.addAttribute("pagina", pagina);
        model.addAttribute("preguntas", pagina.getContent());
        model.addAttribute("tematicas", tematicaService.listarTodas());
        model.addAttribute("tematicaId", tematicaId);
        model.addAttribute("tipo", tipo);
        model.addAttribute("size", size);
        return "preguntas/listar";
    }

    @GetMapping("/nueva")
    public String elegirTipo() {
        return "preguntas/elegir-tipo";
    }

    @GetMapping("/nueva/verdadero-falso")
    public String nuevaVF(Model model) {
        model.addAttribute("pregunta", new PreguntaVerdadeiroFalso());
        model.addAttribute("tematicas", tematicaService.listarTodas());
        return "preguntas/formulario-vf";
    }

    @GetMapping("/nueva/seleccion-unica")
    public String nuevaSU(Model model) {
        model.addAttribute("pregunta", new PreguntaSeleccionUnica());
        model.addAttribute("tematicas", tematicaService.listarTodas());
        return "preguntas/formulario-su";
    }

    @GetMapping("/nueva/seleccion-multiple")
    public String nuevaSM(Model model) {
        model.addAttribute("pregunta", new PreguntaSeleccionMultiple());
        model.addAttribute("tematicas", tematicaService.listarTodas());
        return "preguntas/formulario-sm";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Pregunta pregunta = preguntaService.obtenerPorId(id);
        model.addAttribute("pregunta", pregunta);
        model.addAttribute("tematicas", tematicaService.listarTodas());
        if (pregunta instanceof PreguntaVerdadeiroFalso) {
            return "preguntas/formulario-vf";
        } else if (pregunta instanceof PreguntaSeleccionUnica) {
            return "preguntas/formulario-su";
        } else if (pregunta instanceof PreguntaSeleccionMultiple) {
            return "preguntas/formulario-sm";
        }
        return "preguntas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("pregunta") Pregunta pregunta,
                          BindingResult result,
                          RedirectAttributes redirect,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tematicas", tematicaService.listarTodas());
            if (pregunta instanceof PreguntaVerdadeiroFalso) {
                return "preguntas/formulario-vf";
            } else if (pregunta instanceof PreguntaSeleccionUnica) {
                return "preguntas/formulario-su";
            } else if (pregunta instanceof PreguntaSeleccionMultiple) {
                return "preguntas/formulario-sm";
            }
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