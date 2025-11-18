package br.com.equihire.service;

import br.com.equihire.exception.CandidatoNaoEncontradoException;
import br.com.equihire.exception.EmailDuplicadoException;
import br.com.equihire.model.entities.Candidato;
import br.com.equihire.model.repositories.CandidatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatoService {

    private final CandidatoRepository repository;

    public CandidatoService(CandidatoRepository repository) {
        this.repository = repository;
    }

    public List<Candidato> listarTodos() {
        return repository.findAll();
    }

    public Candidato buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CandidatoNaoEncontradoException("Candidato com ID " + id + " não encontrado."));
    }

    public Candidato criar(Candidato candidato) {
        if (repository.findByEmailIgnoreCase(candidato.getEmail()).isPresent()) {
            throw new EmailDuplicadoException("Já existe um candidato cadastrado com este email.");
        }
        return repository.save(candidato);
    }

    public Candidato atualizar(Long id, Candidato dados) {
        Candidato atual = buscarPorId(id);

        if (!atual.getEmail().equalsIgnoreCase(dados.getEmail()) &&
                repository.findByEmailIgnoreCase(dados.getEmail()).isPresent()) {
            throw new EmailDuplicadoException("O novo email já está em uso por outro candidato.");
        }

        atual.setNome(dados.getNome());
        atual.setEmail(dados.getEmail());
        atual.setAreaAtuacao(dados.getAreaAtuacao());
        atual.setNivelCarreira(dados.getNivelCarreira());
        atual.setResumoExperiencia(dados.getResumoExperiencia());

        return repository.save(atual);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new CandidatoNaoEncontradoException("Candidato com ID " + id + " não pode ser excluído, pois não foi encontrado.");
        }
        repository.deleteById(id);
    }
}
