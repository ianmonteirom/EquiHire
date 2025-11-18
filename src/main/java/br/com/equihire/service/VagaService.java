package br.com.equihire.service;

import br.com.equihire.exception.TituloDuplicadoException;
import br.com.equihire.exception.VagaNaoEncontradaException;
import br.com.equihire.model.Vaga;
import br.com.equihire.repository.VagaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {

    private final VagaRepository repository;

    public VagaService(VagaRepository repository) {
        this.repository = repository;
    }

    public List<Vaga> listarTodos() {
        return repository.findAll();
    }

    public Vaga buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new VagaNaoEncontradaException("Vaga com ID " + id + " não encontrada."));
    }

    public Vaga criar(Vaga vaga) {
        if (repository.findByTituloIgnoreCase(vaga.getTitulo()).isPresent()) {
            throw new TituloDuplicadoException("Já existe uma vaga cadastrada com este título.");
        }
        return repository.save(vaga);
    }

    public Vaga atualizar(Long id, Vaga dados) {
        Vaga atual = buscarPorId(id);

        if (!atual.getTitulo().equalsIgnoreCase(dados.getTitulo()) &&
                repository.findByTituloIgnoreCase(dados.getTitulo()).isPresent()) {
            throw new TituloDuplicadoException("O novo título de vaga já está em uso.");
        }

        atual.setTitulo(dados.getTitulo());
        atual.setDescricao(dados.getDescricao());
        atual.setCargaHorariaSemanal(dados.getCargaHorariaSemanal());
        atual.setNivel(dados.getNivel());
        atual.setAreaAtuacao(dados.getAreaAtuacao());
        atual.setRemota(dados.getRemota());

        return repository.save(atual);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new VagaNaoEncontradaException("Vaga com ID " + id + " não pode ser excluída, pois não foi encontrada.");
        }
        repository.deleteById(id);
    }
}
