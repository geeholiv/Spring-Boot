package com.devsenai2A.petshop.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonAlias;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonAlias({"id"})
    private Integer id_produto;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(nullable = false)
    private Double preco;

    @Column(columnDefinition = "LONGTEXT")
    private String imagem;

    @JsonAlias({"qtdEstoque", "quantidadeEstoque", "quantidade_estoque", "estoque"})
    private Integer qtd_estoque;

    private Boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    // GETTERS E SETTERS

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public Integer getId() {
        return id_produto;
    }

    public void setId(Integer id) {
        this.id_produto = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Object preco) {
        if (preco == null) {
            this.preco = null;
            return;
        }

        if (preco instanceof Number numero) {
            this.preco = numero.doubleValue();
            return;
        }

        this.preco = Double.valueOf(preco.toString().replace(",", "."));
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Integer getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(Integer qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public Integer getQtdEstoque() {
        return qtd_estoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtd_estoque = qtdEstoque;
    }

    public Integer getQuantidadeEstoque() {
        return qtd_estoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.qtd_estoque = quantidadeEstoque;
    }

    public Integer getEstoque() {
        return qtd_estoque;
    }

    public void setEstoque(Integer estoque) {
        this.qtd_estoque = estoque;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
