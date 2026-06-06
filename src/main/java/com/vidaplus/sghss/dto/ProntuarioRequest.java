package com.vidaplus.sghss.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProntuarioRequest {
    private Long pacienteId;
    private Long profissionalId;
    private Long consultaId;
    private String diagnostico;
    private String tratamento;
    private String observacoes;
    private String anexos;
}
