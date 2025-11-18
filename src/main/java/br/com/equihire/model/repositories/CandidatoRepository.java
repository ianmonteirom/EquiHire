package br.com.equihire.model.repositories;

import br.com.equihire.model.entities.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    Optional<Candidato> findByEmailIgnoreCase(String email);
}
