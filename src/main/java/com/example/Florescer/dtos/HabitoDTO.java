package com.example.Florescer.dtos;

import lombok.Data;

@Data
public class HabitoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String frequencia;
    private boolean ativo;
    private Long usuarioId;
}
