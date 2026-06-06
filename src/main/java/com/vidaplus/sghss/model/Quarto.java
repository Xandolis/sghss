package com.vidaplus.sghss.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String tipo; // ENFERMARIA, SUITE, UTI, etc
    private Integer totalLeitos;
    private Integer leitosDisponiveis;
}
