package com.example.Florescer.services;

import com.example.Florescer.models.Habito;
import com.example.Florescer.repositories.HabitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitoService {

    @Autowired
    private HabitoRepository habitoRepository;

    public List<Habito> listarTodos() {
        return habitoRepository.findAll();
    }

    public Optional<Habito> buscarPorId(Long id) {
        return habitoRepository.findById(id);
    }

    public Habito salvar(Habito habito) {
        return habitoRepository.save(habito);
    }

    public void deletar(Long id) {
        habitoRepository.deleteById(id);
    }
}
