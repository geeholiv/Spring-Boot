package com.devsenai2A.petshop.controllers;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.devsenai2A.petshop.entities.Categoria;
import com.devsenai2A.petshop.entities.Produto;
import com.devsenai2A.petshop.repositories.CategoriaRepository;
import com.devsenai2A.petshop.services.ProdutoService;

@RestController
@RequestMapping({"/produtos", "/produto"})
@CrossOrigin("*")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Produto criar(@RequestBody Produto produto) {
        if (produto.getId_produto() != null) {
            Produto editado = produtoService.editar(produto.getId_produto(), produto);

            if (editado == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado.");
            }

            return editado;
        }

        return produtoService.criar(produto);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Produto criarComImagem(
            @RequestParam Map<String, String> dados,
            @RequestParam(required = false) MultipartFile imagem) throws IOException {

        Produto produto = montarProduto(dados, imagem);

        if (produto.getId_produto() != null) {
            Produto editado = produtoService.editar(produto.getId_produto(), produto);

            if (editado == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado.");
            }

            return editado;
        }

        return produtoService.criar(produto);
    }

    @GetMapping
    public List<Produto> listar() {
        return produtoService.listar();
    }

    @GetMapping("/categoria/{idCategoria}")
    public List<Produto> getProdutosByCategoria(@PathVariable String idCategoria) {
        return produtoService.getProdutosPorCategoria(idCategoria);
    }

    @GetMapping("/buscar")
    public List<Produto> buscarProdutosPorNome(@RequestParam String nome) {
        return produtoService.buscarProdutosPorNome(nome);
    }

    @GetMapping("/buscar/agrupado")
    public Map<String, Object> buscarProdutosPorNomeAgrupados(@RequestParam String nome) {
        return produtoService.buscarProdutosPorNomeAgrupados(nome);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Produto editar(@RequestBody Produto produto) {
        Integer id = produto.getId_produto();

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe o id_produto do produto para editar.");
        }

        Produto editado = produtoService.editar(id, produto);

        if (editado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado.");
        }

        return editado;
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Produto editarComImagem(
            @RequestParam Map<String, String> dados,
            @RequestParam(required = false) MultipartFile imagem) throws IOException {

        Produto produto = montarProduto(dados, imagem);
        Integer id = produto.getId_produto();

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe o id_produto do produto para editar.");
        }

        Produto editado = produtoService.editar(id, produto);

        if (editado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado.");
        }

        return editado;
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Produto editarPorId(@PathVariable Integer id, @RequestBody Produto produto) {
        Produto editado = produtoService.editar(id, produto);

        if (editado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado.");
        }

        return editado;
    }

    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Produto editarPorIdComImagem(
            @PathVariable Integer id,
            @RequestParam Map<String, String> dados,
            @RequestParam(required = false) MultipartFile imagem) throws IOException {

        Produto editado = produtoService.editar(id, montarProduto(dados, imagem));

        if (editado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado.");
        }

        return editado;
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        produtoService.excluir(id);
    }

    @DeleteMapping
    public void excluirPorParametro(@RequestParam Map<String, String> dados) {
        Integer id = converterInteiro(primeiroValor(dados, "id", "id_produto"));

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe o id do produto para excluir.");
        }

        produtoService.excluir(id);
    }

    private Produto montarProduto(Map<String, String> dados, MultipartFile imagem) throws IOException {
        Produto produto = new Produto();
        produto.setId_produto(converterInteiro(primeiroValor(dados, "id_produto", "id")));
        produto.setNome(primeiroValor(dados, "nome"));
        produto.setPreco(primeiroValor(dados, "preco"));
        produto.setDescricao(primeiroValor(dados, "descricao"));
        produto.setQtd_estoque(converterInteiro(primeiroValor(
                dados,
                "qtd_estoque",
                "qtdEstoque",
                "quantidadeEstoque",
                "quantidade_estoque",
                "estoque")));

        Categoria categoriaEncontrada = buscarCategoria(primeiroValor(dados, "categoria", "id_categoria", "categoriaId"));
        produto.setCategoria(categoriaEncontrada);

        if (imagem != null && !imagem.isEmpty()) {
            String base64 = Base64.getEncoder().encodeToString(imagem.getBytes());
            produto.setImagem("data:" + imagem.getContentType() + ";base64," + base64);
        } else {
            produto.setImagem(primeiroValor(dados, "imagem"));
        }

        return produto;
    }

    private String primeiroValor(Map<String, String> dados, String... nomes) {
        for (String nome : nomes) {
            String valor = dados.get(nome);

            if (valor != null && !valor.isBlank()) {
                return valor;
            }
        }

        return null;
    }

    private Integer converterInteiro(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }

        return Integer.valueOf(valor);
    }

    private Categoria buscarCategoria(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            return null;
        }

        try {
            Long id = Long.valueOf(categoria);
            return categoriaRepository.findById(id).orElse(null);
        } catch (NumberFormatException ex) {
            return categoriaRepository.findAll().stream()
                    .filter(item -> item.getNome() != null && item.getNome().equalsIgnoreCase(categoria))
                    .findFirst()
                    .orElse(null);
        }
    }
}
