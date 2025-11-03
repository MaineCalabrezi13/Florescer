package com.example.Florescer.dtos;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class TarefaDTO {

    private Long id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    private String descricao;

    @NotNull(message = "Data e hora são obrigatórias")
    private LocalDateTime dataHora;

    private boolean concluida;

    @NotNull(message = "HabitoId é obrigatório")
    private Long habitoId;
}
