package com.devsenai2A.petshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsenai2A.petshop.entities.Categoria;
import com.devsenai2A.petshop.repositories.CategoriaRepository;

@Service
public class CategoriaService {
   
    @Autowired
    private CategoriaRepository repository;
   
    public List<Categoria> listarTodos(){
        return repository.findAll();
    }
   //post
    public Categoria cadastrar(Categoria categoria) {
        return repository.save(categoria);
    }
    //get
    public Categoria buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
   //put
    public Categoria atualizar(Long id, Categoria novaCategoria) {
        Categoria categoria = buscarPorId(id);
        if (categoria != null) {
            categoria.setNome(novaCategoria.getNome());
            categoria.setDescricao(novaCategoria.getDescricao());
            return repository.save(categoria);
        }
        return null;
    }
    //delete
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}