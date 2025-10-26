package com.example.Florescer.controllers;
import com.example.Florescer.models.Tarefa;
import com.example.Florescer.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> listarTarefas() {
        return tarefaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaService.buscarPorId(id);
        return tarefa.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaService.salvar(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        Optional<Tarefa> tarefa = tarefaService.buscarPorId(id);
        if (tarefa.isPresent()) {
            Tarefa existente = tarefa.get();
            existente.setTitulo(tarefaAtualizada.getTitulo());
            existente.setDescricao(tarefaAtualizada.getDescricao());
            existente.setDataHora(tarefaAtualizada.getDataHora());
            existente.setConcluida(tarefaAtualizada.isConcluida());
            existente.setUsuario(tarefaAtualizada.getUsuario());
            return ResponseEntity.ok(tarefaService.salvar(existente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}