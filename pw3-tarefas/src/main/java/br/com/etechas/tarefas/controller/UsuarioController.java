package br.com.etechas.tarefas.controller;

import br.com.etechas.tarefas.entity.Usuario;
import br.com.etechas.tarefas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar() {return usuarioService.listar();}


    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        Usuario salvo = usuarioService.registrar(usuario);
        return ResponseEntity.ok(salvo);
    }




}
