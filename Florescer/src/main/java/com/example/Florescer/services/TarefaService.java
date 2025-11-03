package com.example.Florescer.services;

import com.example.Florescer.models.Tarefa;
import com.example.Florescer.repositories.TarefaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository){
        this.tarefaRepository = tarefaRepository;
    }

    public List<Tarefa> getAll(){
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> getOne(Long id){
        return tarefaRepository.findById(id);
    }

    public Tarefa create(Tarefa tarefa){
        return tarefaRepository.save(tarefa);
    }

    public Tarefa update(Long id, Tarefa tarefaAtualizada){
        return tarefaRepository.findById(id)
                .map(tarefa -> {
                    tarefa.setTitulo(tarefaAtualizada.getTitulo());
                    tarefa.setDescricao(tarefaAtualizada.getDescricao());
                    tarefa.setDataHora(tarefaAtualizada.getDataHora());
                    tarefa.setConcluida(tarefaAtualizada.isConcluida());
                    return tarefaRepository.save(tarefa);
                }).orElse(null);
    }

    public boolean delete(Long id){
        if(tarefaRepository.existsById(id)){
            tarefaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
