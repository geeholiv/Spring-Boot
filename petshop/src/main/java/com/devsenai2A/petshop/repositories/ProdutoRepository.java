package com.devsenai2A.petshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsenai2A.petshop.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
