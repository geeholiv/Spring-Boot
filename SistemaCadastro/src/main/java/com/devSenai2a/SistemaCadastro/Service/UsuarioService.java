package com.devSenai2a.SistemaCadastro.Service;

import com.devSenai2a.SistemaCadastro.Entitys.Usuario;
import com.devSenai2a.SistemaCadastro.Repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listarTodos(){
        return repository.findAll();
    }

    public Usuario cadastrar(Usuario usuario) {
        return repository.save(usuario);
    }
}