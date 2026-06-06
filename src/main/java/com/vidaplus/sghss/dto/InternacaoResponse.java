package com.vidaplus.sghss.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class InternacaoResponse {
    private Long id;
    private Long pacienteId;
    private Long leitoId;
    private Long profissionalResponsavelId;
    private LocalDateTime dataAdmissao;
    private LocalDateTime dataAlta;
    private String motivo;
    private String observacoes;
}
