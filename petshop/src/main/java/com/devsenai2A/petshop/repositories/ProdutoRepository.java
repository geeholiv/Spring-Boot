package com.devsenai2A.petshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsenai2A.petshop.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("""
            select produto from Produto produto
            left join fetch produto.categoria
            where (produto.ativo = true or produto.ativo is null)
            and lower(produto.nome) like lower(concat('%', :nome, '%'))
            order by produto.categoria.nome, produto.nome
            """)
    List<Produto> findProdutosAtivosPorNome(@Param("nome") String nome);

    @Query("""
            select produto from Produto produto
            where (produto.ativo = true or produto.ativo is null)
            and (
                (:idCategoria is not null and produto.categoria.id = :idCategoria)
                or (:nomeCategoria is not null and lower(produto.categoria.nome) = lower(:nomeCategoria))
            )
            """)
    List<Produto> findProdutosAtivosPorCategoria(
            @Param("idCategoria") Long idCategoria,
            @Param("nomeCategoria") String nomeCategoria);
}
