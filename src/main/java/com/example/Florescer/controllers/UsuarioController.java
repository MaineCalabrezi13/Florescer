package com.example.Florescer.controllers;

import com.example.Florescer.dtos.UsuarioDTO;
import com.example.Florescer.models.Usuario;
import com.example.Florescer.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAll() {
        List<UsuarioDTO> usuarios = usuarioService.getAll()
                .stream()
                .map(u -> new UsuarioDTO(u.getId(), u.getNome(), u.getEmail(), null))
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getOne(@PathVariable Long id) {
        Usuario usuario = usuarioService.getOne(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        return ResponseEntity.ok(new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), null));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO dto) {
        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .build();
        Usuario criado = usuarioService.create(usuario);
        dto.setId(criado.getId());
        dto.setSenha(null); // não retornar senha
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        Usuario usuarioAtualizado = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .build();
        Usuario atualizado = usuarioService.update(id, usuarioAtualizado);
        if (atualizado == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        dto.setId(atualizado.getId());
        dto.setSenha(null);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (usuarioService.delete(id)) return ResponseEntity.noContent().build();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
    }
}
