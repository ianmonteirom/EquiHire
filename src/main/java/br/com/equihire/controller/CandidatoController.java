package br.com.equihire.controller;

import br.com.equihire.exception.CandidatoNaoEncontradoException;
import br.com.equihire.exception.EmailDuplicadoException;
import br.com.equihire.model.entities.Candidato;
import br.com.equihire.service.CandidatoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/candidatos")
public class CandidatoController {

    private final CandidatoService service;

    public CandidatoController(CandidatoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", service.listarTodos());
        return "candidatos/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("candidato", new Candidato());
        return "candidatos/form";
    }

    @PostMapping
    public String criar(@Valid @ModelAttribute("candidato") Candidato candidato,
                        BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()) return "candidatos/form";

        try {
            service.criar(candidato);
            ra.addFlashAttribute("msg_sucesso", "Candidato criado com sucesso!");
        } catch (EmailDuplicadoException e) {
            ra.addFlashAttribute("msg_erro", e.getMessage());
            return "redirect:/candidatos/novo";
        }

        return "redirect:/candidatos";
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model, RedirectAttributes ra) {
        try {
            Candidato candidato = service.buscarPorId(id);
            model.addAttribute("candidato", candidato);
            return "candidatos/form";
        } catch (CandidatoNaoEncontradoException e) {
            ra.addFlashAttribute("msg_erro", e.getMessage());
            return "redirect:/candidatos";
        }
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("candidato") Candidato dados,
                            BindingResult br, RedirectAttributes ra, Model model) {
        if (br.hasErrors()) return "candidatos/form";

        try {
            service.atualizar(id, dados);
            ra.addFlashAttribute("msg_sucesso", "Candidato atualizado com sucesso!");
        } catch (CandidatoNaoEncontradoException | EmailDuplicadoException e) {
            ra.addFlashAttribute("msg_erro", e.getMessage());
            return "redirect:/candidatos";
        }

        return "redirect:/candidatos";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
        try {
            service.excluir(id);
            ra.addFlashAttribute("msg_sucesso", "Candidato excluído.");
        } catch (CandidatoNaoEncontradoException e) {
            ra.addFlashAttribute("msg_erro", e.getMessage());
        }
        return "redirect:/candidatos";
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model, RedirectAttributes ra) {
        try {
            Candidato c = service.buscarPorId(id);
            model.addAttribute("candidato", c);
            return "candidatos/detalhes";
        } catch (CandidatoNaoEncontradoException e) {
            ra.addFlashAttribute("msg_erro", e.getMessage());
            return "redirect:/candidatos";
        }
    }
}
