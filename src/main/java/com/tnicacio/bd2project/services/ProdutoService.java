package com.tnicacio.bd2project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tnicacio.bd2project.entities.Produto;
import com.tnicacio.bd2project.repositories.ProdutoRepository;
import com.tnicacio.bd2project.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	public List<Produto> findAll(){
		return repository.findAllByOrderByDescricaoAsc();
	}
	
	public Produto findById(Integer id) {
		Optional<Produto> produto = repository.findById(id);
		return produto.orElseThrow(() -> new ResourceNotFoundException(id));
	}
}
