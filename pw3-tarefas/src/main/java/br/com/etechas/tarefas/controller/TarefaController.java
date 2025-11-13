package br.com.etechas.tarefas.controller;

import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.service.TarefaService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "http://localhost:4200")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> listar() {
        return tarefaService.listar();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            boolean existe = tarefaService.deletarPorId(id);
            if (existe) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<?> criarTarefa(@RequestBody Tarefa tarefa) {
        if (tarefa.getDataLimite() == null) {
            return ResponseEntity.badRequest().body("A data limite não pode ser nula.");
        }

        if (tarefa.getDataLimite().isBefore(LocalDate.now())) {
            return ResponseEntity.badRequest().body("A data não pode ser anterior a atual.");
        }

        try {
            Tarefa novaTarefa = tarefaService.add(tarefa);
            return ResponseEntity.ok(novaTarefa);} catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

