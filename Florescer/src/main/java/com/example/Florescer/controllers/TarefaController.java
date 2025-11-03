package com.example.Florescer.controllers;

import com.example.Florescer.dtos.TarefaDTO;
import com.example.Florescer.models.Habito;
import com.example.Florescer.models.Tarefa;
import com.example.Florescer.services.HabitoService;
import com.example.Florescer.services.TarefaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;
    private final HabitoService habitoService;

    public TarefaController(TarefaService tarefaService, HabitoService habitoService) {
        this.tarefaService = tarefaService;
        this.habitoService = habitoService;
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> getAll() {
        List<TarefaDTO> tarefas = tarefaService.getAll().stream()
                .map(t -> {
                    TarefaDTO dto = new TarefaDTO();
                    dto.setId(t.getId());
                    dto.setTitulo(t.getTitulo());
                    dto.setDescricao(t.getDescricao());
                    dto.setDataHora(t.getDataHora());
                    dto.setConcluida(t.isConcluida());
                    dto.setHabitoId(t.getHabito().getId());
                    return dto;
                }).collect(Collectors.toList());
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> getOne(@PathVariable Long id) {
        Tarefa tarefa = tarefaService.getOne(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
        TarefaDTO dto = new TarefaDTO();
        dto.setId(tarefa.getId());
        dto.setTitulo(tarefa.getTitulo());
        dto.setDescricao(tarefa.getDescricao());
        dto.setDataHora(tarefa.getDataHora());
        dto.setConcluida(tarefa.isConcluida());
        dto.setHabitoId(tarefa.getHabito().getId());
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> create(@Valid @RequestBody TarefaDTO dto) {
        Habito habito = habitoService.getOne(dto.getHabitoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hábito não encontrado"));
        Tarefa tarefa = Tarefa.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .dataHora(dto.getDataHora())
                .concluida(dto.isConcluida())
                .habito(habito)
                .build();
        Tarefa criado = tarefaService.create(tarefa);
        dto.setId(criado.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> update(@PathVariable Long id, @Valid @RequestBody TarefaDTO dto) {
        Tarefa tarefa = tarefaService.getOne(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setDataHora(dto.getDataHora());
        tarefa.setConcluida(dto.isConcluida());
        Tarefa atualizado = tarefaService.update(id, tarefa);
        dto.setId(atualizado.getId());
        dto.setHabitoId(atualizado.getHabito().getId());
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (tarefaService.delete(id)) return ResponseEntity.noContent().build();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
    }
}
