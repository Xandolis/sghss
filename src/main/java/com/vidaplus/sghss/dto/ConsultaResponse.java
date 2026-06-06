package com.vidaplus.sghss.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ConsultaResponse {
    private Long id;
    private Long pacienteId;
    private Long profissionalId;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String status;
    private String observacoes;
}
