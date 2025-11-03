package com.example.Florescer.services;

import com.example.Florescer.models.Usuario;
import com.example.Florescer.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getOne(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(usuarioAtualizado.getNome());
                    usuario.setEmail(usuarioAtualizado.getEmail());
                    usuario.setSenha(usuarioAtualizado.getSenha());
                    return usuarioRepository.save(usuario);
                })
                .orElse(null);
    }

    public boolean delete(Long id) {
        if(usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
