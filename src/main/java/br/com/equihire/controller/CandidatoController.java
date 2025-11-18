package br.com.equihire.controller;

import br.com.equihire.model.entities.Candidato;
import br.com.equihire.service.CandidatoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    private final CandidatoService service;

    public CandidatoController(CandidatoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Candidato> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Candidato buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Candidato criar(@Valid @RequestBody Candidato candidato) {
        return service.criar(candidato);
    }

    @PutMapping("/{id}")
    public Candidato atualizar(@PathVariable Long id, @Valid @RequestBody Candidato dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
