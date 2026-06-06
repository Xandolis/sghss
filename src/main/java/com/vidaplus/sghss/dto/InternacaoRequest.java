package com.vidaplus.sghss.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class InternacaoRequest {
    private Long pacienteId;
    private Long leitoId;
    private Long profissionalResponsavelId;
    private String motivo;
    private String observacoes;
}
