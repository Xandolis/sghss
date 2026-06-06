package com.vidaplus.sghss.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeTrabalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional;

    private String diaSemana; // SEGUNDA, TERÇA, ..., DOMINGO
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Integer duracaoConsultaMinutos; // Ex: 30 minutos
}
