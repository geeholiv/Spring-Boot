package com.devsenai2A.petshop.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Produto> getProdutosPorCategoria(String categoria) {
        Long idCategoria = null;
        String nomeCategoria = null;

        try {
            idCategoria = Long.valueOf(categoria);
        } catch (NumberFormatException ex) {
            nomeCategoria = categoria;
        }

        return produtoRepository.findProdutosAtivosPorCategoria(idCategoria, nomeCategoria);
    }

    public List<Produto> buscarProdutosPorNome(String nome) {
        return produtoRepository.findProdutosAtivosPorNome(nome);
    }

    public Map<String, Object> buscarProdutosPorNomeAgrupados(String nome) {
        List<Produto> produtos = buscarProdutosPorNome(nome);
        Map<String, List<Produto>> produtosPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(
                        produto -> produto.getCategoria() != null ? produto.getCategoria().getNome() : "Sem categoria",
                        LinkedHashMap::new,
                        Collectors.toList()));

        Map<String, Object> resultado = new LinkedHashMap<>();
        resultado.put("busca", nome);
        resultado.put("total", produtos.size());
        resultado.put("categorias", produtosPorCategoria);

        return resultado;
    }

    public Produto editar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto editar(Integer id, Produto produto) {
        Produto produtoExistente = produtoRepository.findById(id).orElse(null);

        if (produtoExistente == null) {
            return null;
        }

        if (produto.getNome() != null) {
            produtoExistente.setNome(produto.getNome());
        }

        if (produto.getDescricao() != null) {
            produtoExistente.setDescricao(produto.getDescricao());
        }

        if (produto.getPreco() != null) {
            produtoExistente.setPreco(produto.getPreco());
        }

        if (produto.getQtd_estoque() != null) {
            produtoExistente.setQtd_estoque(produto.getQtd_estoque());
        }

        if (produto.getCategoria() != null) {
            produtoExistente.setCategoria(produto.getCategoria());
        }

        if (produto.getImagem() != null && !produto.getImagem().isBlank()) {
            produtoExistente.setImagem(produto.getImagem());
        }

        if (produto.getAtivo() != null) {
            produtoExistente.setAtivo(produto.getAtivo());
        }

        return produtoRepository.save(produtoExistente);
    }

    public void excluir(Integer id) {
        produtoRepository.deleteById(id);
    }
}
