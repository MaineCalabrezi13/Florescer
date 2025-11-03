package com.example.Florescer.controllers;

import com.example.Florescer.dtos.HabitoDTO;
import com.example.Florescer.models.Habito;
import com.example.Florescer.models.Usuario;
import com.example.Florescer.services.HabitoService;
import com.example.Florescer.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/habitos")
public class HabitoController {

    private final HabitoService habitoService;
    private final UsuarioService usuarioService;

    public HabitoController(HabitoService habitoService, UsuarioService usuarioService) {
        this.habitoService = habitoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<Page<HabitoDTO>> getAll(@RequestParam(required = false) String nome, Pageable pageable) {
        Page<HabitoDTO> habitos = habitoService.getAll(nome, pageable)
                .map(h -> {
                    HabitoDTO dto = new HabitoDTO();
                    dto.setId(h.getId());
                    dto.setNome(h.getNome());
                    dto.setDescricao(h.getDescricao());
                    dto.setFrequencia(h.getFrequencia());
                    dto.setAtivo(h.isAtivo());
                    dto.setUsuarioId(h.getUsuario().getId());
                    return dto;
                });
        return ResponseEntity.ok(habitos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitoDTO> getOne(@PathVariable Long id) {
        Habito habito = habitoService.getOne(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hábito não encontrado"));
        HabitoDTO dto = new HabitoDTO();
        dto.setId(habito.getId());
        dto.setNome(habito.getNome());
        dto.setDescricao(habito.getDescricao());
        dto.setFrequencia(habito.getFrequencia());
        dto.setAtivo(habito.isAtivo());
        dto.setUsuarioId(habito.getUsuario().getId());
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<HabitoDTO> create(@Valid @RequestBody HabitoDTO dto) {
        Usuario usuario = usuarioService.getOne(dto.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        Habito habito = Habito.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .frequencia(dto.getFrequencia())
                .ativo(dto.isAtivo())
                .usuario(usuario)
                .build();
        Habito criado = habitoService.create(habito);
        dto.setId(criado.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitoDTO> update(@PathVariable Long id, @Valid @RequestBody HabitoDTO dto) {
        Habito habito = habitoService.getOne(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hábito não encontrado"));
        habito.setNome(dto.getNome());
        habito.setDescricao(dto.getDescricao());
        habito.setFrequencia(dto.getFrequencia());
        habito.setAtivo(dto.isAtivo());
        Habito atualizado = habitoService.update(id, habito);
        dto.setId(atualizado.getId());
        dto.setUsuarioId(atualizado.getUsuario().getId());
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (habitoService.delete(id)) return ResponseEntity.noContent().build();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hábito não encontrado");
    }
}
