package com.example.Florescer.services;

import com.example.Florescer.models.Habito;
import com.example.Florescer.models.Tarefa;
import com.example.Florescer.repositories.HabitoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class HabitoService {

    private final HabitoRepository habitoRepository;

    public HabitoService(HabitoRepository habitoRepository) {
        this.habitoRepository = habitoRepository;
    }

    @Cacheable("habitos")
    public Page<Habito> getAll(String nome, Pageable pageable) {
        if (nome != null && !nome.isEmpty()) {
            return habitoRepository.findByNomeContainingIgnoreCase(nome, pageable);
        }
        return habitoRepository.findAll(pageable);
    }

    public Optional<Habito> getOne(Long id) {
        return habitoRepository.findById(id);
    }

    @CacheEvict(value = "habitos", allEntries = true)
    public Habito create(Habito habito) {
        return habitoRepository.save(habito);
    }

    @CacheEvict(value = "habitos", allEntries = true)
    public Habito update(Long id, Habito habito) {
        return habitoRepository.findById(id)
                .map(h -> {
                    h.setNome(habito.getNome());
                    h.setDescricao(habito.getDescricao());
                    h.setFrequencia(habito.getFrequencia());
                    h.setAtivo(habito.isAtivo());
                    return habitoRepository.save(h);
                }).orElse(null);
    }

    @CacheEvict(value = "habitos", allEntries = true)
    public boolean delete(Long id) {
        if (habitoRepository.existsById(id)) {
            habitoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ------------------------------
    // Cálculo de Progresso
    // ------------------------------
    public Map<String, Object> getProgresso(Long habitoId) {
        Habito habito = habitoRepository.findById(habitoId)
                .orElseThrow(() -> new RuntimeException("Hábito não encontrado"));

        int total = habito.getTarefas().size();
        int concluidas = (int) habito.getTarefas().stream()
                .filter(Tarefa::isConcluida)
                .count();

        double porcentagem = total == 0 ? 0 : (concluidas * 100.0) / total;

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("habitoId", habitoId);
        resultado.put("totalTarefas", total);
        resultado.put("concluidas", concluidas);
        resultado.put("porcentagem", porcentagem);

        return resultado;
    }
}
