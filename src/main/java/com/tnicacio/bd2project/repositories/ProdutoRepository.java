package com.tnicacio.bd2project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnicacio.bd2project.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	public List<Produto> findAllByOrderByDescricaoAsc();
	
	public List<Produto> findAllByDtInativacaoIsNullOrderByDescricaoAsc();
}
