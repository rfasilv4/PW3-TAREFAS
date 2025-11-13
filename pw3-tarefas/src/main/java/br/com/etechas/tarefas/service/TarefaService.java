package br.com.etechas.tarefas.service;

import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.enums.StatusEnum;
import br.com.etechas.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    private StatusEnum status;

    public List<Tarefa> listar() {
        return repository.findAll();
    }

    public boolean deletarPorId(Long id) {
        Optional<Tarefa> tarefaOpt = repository.findById(id);

        if (!tarefaOpt.isPresent()) {
            return false;
        }

        Tarefa tarefa = tarefaOpt.get();

        if (tarefa.getStatus() != StatusEnum.PENDENTE) {
            throw new RuntimeException("Tarefa em progresso ou concluída");
        }

        repository.deleteById(id);
        return true;
    }

    public Tarefa add(Tarefa tarefa){
        if (tarefa.getDataLimite() == null) {
            throw new IllegalArgumentException("A data limite não pode ser nula.");
        }

        if(tarefa.getDataLimite().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("A data não pode ser anterior a atual.");
        }
        return repository.save(tarefa);
    }
}

