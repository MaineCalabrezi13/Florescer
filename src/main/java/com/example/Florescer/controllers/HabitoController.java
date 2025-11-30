package com.example.Florescer.controllers;

import com.example.Florescer.models.Habito;
import com.example.Florescer.services.HabitoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/habitos")
public class HabitoController {

    private final HabitoService habitoService;

    public HabitoController(HabitoService habitoService) {
        this.habitoService = habitoService;
    }

    // LISTAR HABITOS (com paginação e filtro opcional)
    @GetMapping
    public Page<Habito> getAll(
            @RequestParam(required = false) String nome,
            Pageable pageable
    ) {
        return habitoService.getAll(nome, pageable);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public Habito getOne(@PathVariable Long id) {
        return habitoService.getOne(id)
                .orElseThrow(() -> new RuntimeException("Hábito não encontrado"));
    }

    // CRIAR
    @PostMapping
    public Habito create(@RequestBody Habito habito) {
        return habitoService.create(habito);
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public Habito update(@PathVariable Long id, @RequestBody Habito habito) {
        return habitoService.update(id, habito);
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        habitoService.delete(id);
    }

    // 🔥 PROGRESSO DO HÁBITO
    @GetMapping("/{id}/progresso")
    public Map<String, Object> getProgresso(@PathVariable Long id) {
        return habitoService.getProgresso(id);
    }
}
