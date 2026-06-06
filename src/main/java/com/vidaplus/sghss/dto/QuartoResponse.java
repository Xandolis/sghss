package com.vidaplus.sghss.dto;

import lombok.Data;

@Data
public class QuartoResponse {
    private Long id;
    private String numero;
    private String tipo;
    private Integer totalLeitos;
    private Integer leitosDisponiveis;
}
