package com.vidaplus.sghss.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Internacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "leito_id", nullable = false)
    private Leito leito;

    @ManyToOne
    @JoinColumn(name = "profissional_responsavel_id")
    private Profissional profissionalResponsavel;

    private LocalDateTime dataAdmissao;
    private LocalDateTime dataAlta;
    private String motivo;
    private String observacoes;
}
