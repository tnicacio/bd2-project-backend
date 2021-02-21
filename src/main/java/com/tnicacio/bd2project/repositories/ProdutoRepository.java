package com.tnicacio.bd2project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnicacio.bd2project.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
