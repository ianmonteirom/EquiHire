package br.com.equihire.controller;

import br.com.equihire.exception.TituloDuplicadoException;
import br.com.equihire.exception.VagaNaoEncontradaException;
import br.com.equihire.model.entities.Vaga;
import br.com.equihire.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vagas")
public class VagaController {

    private final VagaService service;

    public VagaController(VagaService service) {
        this.service = service;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Integer.class, "cargaHorariaSemanal", new CustomNumberEditor(Integer.class, true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.setDisallowedFields("id", "criadoEm", "atualizadoEm");
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", service.listarTodos());
        return "vagas/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("vaga", new Vaga());
        return "vagas/form";
    }

    @PostMapping
    public String criar(@Valid @ModelAttribute("vaga") Vaga vaga,
                        BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()) return "vagas/form";

        try {
            service.criar(vaga);
            ra.addFlashAttribute("msg_sucesso", "Vaga criada com sucesso!");
        } catch (TituloDuplicadoException e) {
            ra.addFlashAttribute("msg_erro", e.getMessage());
            return "redirect:/vagas/novo";
        }

        return "redirect:/vagas";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model, RedirectAttributes ra) {
        try {
            Vaga vaga = service.buscarPorId(id);
            model.addAttribute("vaga", vaga);
            return "vagas/form";
        } catch (VagaNaoEncontradaException e) {
            ra.addFlashAttribute("msg_erro", e.getMessage());
            return "redirect:/vagas";
        }
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("vaga") Vaga dados,
                            BindingResult br, RedirectAttributes ra, Model model) {
        if (br.hasErrors()) return "vagas/form";

        try {
            service.atualizar(id, dados);
            ra.addFlashAttribute("msg_sucesso", "Vaga atualizada com sucesso!");
        } catch (VagaNaoEncontradaException | TituloDuplicadoException e) {
            ra.addFlashAttribute("msg_erro", e.getMessage());
            return "redirect:/vagas";
        }

        return "redirect:/vagas";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
        try {
            service.excluir(id);
            ra.addFlashAttribute("msg_sucesso", "Vaga excluída.");
        } catch (VagaNaoEncontradaException e) {
            ra.addFlashAttribute("msg_erro", e.getMessage());
        }
        return "redirect:/vagas";
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model, RedirectAttributes ra) {
        try {
            Vaga v = service.buscarPorId(id);
            model.addAttribute("vaga", v);
            return "vagas/detalhes";
        } catch (VagaNaoEncontradaException e) {
            ra.addFlashAttribute("msg_erro", e.getMessage());
            return "redirect:/vagas";
        }
    }
}
