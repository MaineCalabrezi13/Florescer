package com.example.Florescer.controllers;
import com.example.Florescer.models.Habito;
import com.example.Florescer.services.HabitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habitos")
public class HabitoController {

    @Autowired
    private HabitoService habitoService;


    @GetMapping
    public List<Habito> listarHabitos() {
        return habitoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habito> buscarPorId(@PathVariable Long id) {
        Optional<Habito> habito = habitoService.buscarPorId(id);
        return habito.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Habito> criarHabito(@RequestBody Habito habito) {
        Habito novoHabito = habitoService.salvar(habito);
        return ResponseEntity.status(201).body(novoHabito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habito> atualizarHabito(@PathVariable Long id, @RequestBody Habito habitoAtualizado) {
        Optional<Habito> habitoExistente = habitoService.buscarPorId(id);

        if (habitoExistente.isPresent()) {
            Habito habito = habitoExistente.get();
            habito.setNome(habitoAtualizado.getNome());
            habito.setDescricao(habitoAtualizado.getDescricao());
            habito.setFrequencia(habitoAtualizado.getFrequencia());
            habito.setAtivo(habitoAtualizado.isAtivo());
            habito.setUsuario(habitoAtualizado.getUsuario());

            Habito habitoSalvo = habitoService.salvar(habito);
            return ResponseEntity.ok(habitoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirHabito(@PathVariable Long id) {
        Optional<Habito> habito = habitoService.buscarPorId(id);
        if (habito.isPresent()) {
            habitoService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
