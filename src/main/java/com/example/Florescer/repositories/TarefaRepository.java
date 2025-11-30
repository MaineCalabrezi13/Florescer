package com.example.Florescer.repositories;

import com.example.Florescer.models.Tarefa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("""
        SELECT t FROM Tarefa t
        WHERE (:habitoId IS NULL OR t.habito.id = :habitoId)
        AND (:concluida IS NULL OR t.concluida = :concluida)
        AND (:startDate IS NULL OR t.dataHora >= :startDate)
        AND (:endDate IS NULL OR t.dataHora <= :endDate)
    """)
    Page<Tarefa> filter(Long habitoId,
                        Boolean concluida,
                        LocalDateTime startDate,
                        LocalDateTime endDate,
                        Pageable pageable);

    long countByHabitoId(Long habitoId);

    long countByHabitoIdAndConcluidaTrue(Long habitoId);
}
