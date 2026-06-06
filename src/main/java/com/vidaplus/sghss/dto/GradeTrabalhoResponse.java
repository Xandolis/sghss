package com.vidaplus.sghss.dto;

import lombok.Data;
import java.time.LocalTime;

@Data
public class GradeTrabalhoResponse {
    private Long id;
    private Long profissionalId;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Integer duracaoConsultaMinutos;
}
