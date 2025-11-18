package br.com.equihire.model.entities;

import br.com.equihire.model.enums.NivelCarreira;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "vagas")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O Título da vaga é obrigatório.")
    @Size(max = 120, message = "O título deve ter no máximo 120 caracteres.")
    @Column(nullable = false, unique = true, length = 120)
    private String titulo;

    @NotBlank(message = "A descrição da vaga é obrigatória.")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Min(value = 1, message = "A carga horária deve ser positiva.")
    @Max(value = 60, message = "A carga horária máxima permitida é de 60 horas.")
    @Column(name = "carga_horaria_semanal", nullable = false)
    private Integer cargaHorariaSemanal;

    @NotBlank(message = "O Nível de Carreira é obrigatório.")
    @Column(name = "nivel_carreira", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private NivelCarreira nivel;

    @NotBlank(message = "A área de atuação é obrigatória.")
    @Size(max = 100, message = "A área de atuação deve ter no máximo 100 caracteres.")
    @Column(name = "area_atuacao", length = 100)
    private String areaAtuacao;

    private Boolean remota = false;


    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
        this.atualizadoEm = LocalDateTime.now();
    }
}
