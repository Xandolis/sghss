package com.vidaplus.sghss.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BloqueioResponse {
    private Long id;
    private Long profissionalId;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String motivo;
}
