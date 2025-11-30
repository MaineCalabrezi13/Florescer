package com.example.Florescer.controllers;

import com.example.Florescer.models.Tarefa;
import com.example.Florescer.services.TarefaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    // Criar tarefa associada a um hábito
    @PostMapping
    public ResponseEntity<Tarefa> create(@RequestParam Long habitoId,
                                         @RequestBody Tarefa t) {
        return ResponseEntity.ok(tarefaService.create(t, habitoId));
    }

    // Filtro com paginação e ordenação
    @GetMapping
    public ResponseEntity<Page<Tarefa>> filter(
            @RequestParam(required = false) Long habitoId,
            @RequestParam(required = false) Boolean concluida,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endDate,

            Pageable pageable
    ) {
        return ResponseEntity.ok(
                tarefaService.filter(habitoId, concluida, startDate, endDate, pageable)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> get(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> update(@PathVariable Long id,
                                         @RequestBody Tarefa t) {
        return ResponseEntity.ok(tarefaService.update(id, t));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tarefaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> concluir(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.concluir(id));
    }

    @PutMapping("/{id}/desconcluir")
    public ResponseEntity<Tarefa> desconcluir(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.desconcluir(id));
    }
}
