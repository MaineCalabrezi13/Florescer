package com.example.Florescer.repositories;

import com.example.Florescer.models.Habito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitoRepository extends JpaRepository<Habito, Long> {
    Page<Habito> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
