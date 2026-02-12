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

    public Usuario atualizar(Long id, Usuario dados) {
        Usuario usuario = repository.findById(id).orElse(null);

        if (usuario == null) {
            return null;
        }

        usuario.setNome(dados.getNome());
        usuario.setEmail(dados.getEmail());
        usuario.setSenha(dados.getSenha());
        usuario.setPerfil(dados.getPerfil());
        usuario.setEndereco(dados.getEndereco());
        usuario.setBairro(dados.getBairro());
        usuario.setComplemento(dados.getComplemento());
        usuario.setCep(dados.getCep());
        usuario.setCidade(dados.getCidade());
        usuario.setEstado(dados.getEstado());

        return repository.save(usuario);
    }

    public boolean deletar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

// localhost:8080/usuarios/3 DELETE
        repository.deleteById(id);
        return true;
    }
}