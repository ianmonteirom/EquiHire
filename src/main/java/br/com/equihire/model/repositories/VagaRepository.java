package br.com.equihire.model.repositories;

import br.com.equihire.model.entities.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface VagaRepository extends JpaRepository<Vaga, Long> {

    Optional<Vaga> findByTituloIgnoreCase(String titulo);

    List<Vaga> findByAreaAtuacaoContainingIgnoreCase(String areaAtuacao);
}
