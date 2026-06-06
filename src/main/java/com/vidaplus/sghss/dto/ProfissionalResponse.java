package com.vidaplus.sghss.dto;

import lombok.Data;

@Data
public class ProfissionalResponse {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String especialidade;
    private String crm;
}
