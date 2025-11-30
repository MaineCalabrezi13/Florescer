package com.example.Florescer.services;

import com.example.Florescer.models.Habito;
import com.example.Florescer.models.Tarefa;
import com.example.Florescer.repositories.HabitoRepository;
import com.example.Florescer.repositories.TarefaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final HabitoRepository habitoRepository;

    public TarefaService(TarefaRepository tarefaRepository, HabitoRepository habitoRepository) {
        this.tarefaRepository = tarefaRepository;
        this.habitoRepository = habitoRepository;
    }

    // Criar tarefa associada a um hábito
    public Tarefa create(Tarefa t, Long habitoId) {
        Habito h = habitoRepository.findById(habitoId)
                .orElseThrow(() -> new RuntimeException("Hábito não encontrado"));
        t.setHabito(h);
        return tarefaRepository.save(t);
    }

    // Filtro com paginação e ordenação
    public Page<Tarefa> filter(Long habitoId, Boolean concluida,
                               LocalDateTime startDate, LocalDateTime endDate,
                               Pageable pageable) {
        return tarefaRepository.filter(habitoId, concluida, startDate, endDate, pageable);
    }

    public Tarefa get(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public Tarefa update(Long id, Tarefa updated) {
        return tarefaRepository.findById(id).map(t -> {
            t.setTitulo(updated.getTitulo());
            t.setDescricao(updated.getDescricao());
            t.setDataHora(updated.getDataHora());
            t.setConcluida(updated.isConcluida());
            return tarefaRepository.save(t);
        }).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public void delete(Long id) {
        tarefaRepository.deleteById(id);
    }

    // Marca como concluída
    public Tarefa concluir(Long id) {
        Tarefa t = get(id);
        t.setConcluida(true);
        return tarefaRepository.save(t);
    }

    // Desmarca como concluída
    public Tarefa desconcluir(Long id) {
        Tarefa t = get(id);
        t.setConcluida(false);
        return tarefaRepository.save(t);
    }

    // --------------------------
    //   CÁLCULO DE PROGRESSO
    // --------------------------

    // Percentual de progresso de um hábito (0.0 - 100.0)
    public double progressoPorHabito(Long habitoId) {
        long total = tarefaRepository.countByHabitoId(habitoId);
        if (total == 0) return 0.0;

        long concluidas = tarefaRepository.countByHabitoIdAndConcluidaTrue(habitoId);
        return (concluidas * 100.0) / total;
    }
}

