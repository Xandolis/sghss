package com.vidaplus.sghss.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PacienteRequest {
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String endereco;
    private String numeroUtente;
}
