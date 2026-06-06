package com.vidaplus.sghss.dto;

import lombok.Data;
import java.time.LocalTime;

@Data
public class GradeTrabalhoRequest {
    private Long profissionalId;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Integer duracaoConsultaMinutos;
}
