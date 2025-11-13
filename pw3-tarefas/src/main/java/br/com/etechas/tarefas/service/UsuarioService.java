package br.com.etechas.tarefas.service;

import br.com.etechas.tarefas.entity.Usuario;
import br.com.etechas.tarefas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrar(Usuario usuario) {
        var existe = usuarioRepository.findByUsername(usuario.getUsername());
        if (existe.isPresent()){
            throw new RuntimeException("Nome de usuário já cadastrado");
        }

        var SenhaCifrada= passwordEncoder.encode(usuario.getPassword());
        var salvo = usuarioRepository.save(usuario);
        return salvo;
    }
    public List<Usuario> listar (){return usuarioRepository.findAll();}
}
