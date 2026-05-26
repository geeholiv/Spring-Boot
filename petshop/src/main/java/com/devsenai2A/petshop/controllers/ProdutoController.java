package com.devsenai2A.petshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.devsenai2A.petshop.entities.Produto;
import com.devsenai2A.petshop.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return produtoService.criar(produto);
    }

    @GetMapping
    public List<Produto> listar() {
        return produtoService.listar();
    }

    @PutMapping
    public Produto editar(@RequestBody Produto produto) {
        return produtoService.editar(produto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        produtoService.excluir(id);
    }
}
