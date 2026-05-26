package com.devsenai2A.petshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsenai2A.petshop.entities.Produto;
import com.devsenai2A.petshop.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto criar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto editar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void excluir(Integer id) {
        produtoRepository.deleteById(id);
    }
}
