package com.tnicacio.bd2project.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tnicacio.bd2project.entities.Itens;
import com.tnicacio.bd2project.entities.Produto;
import com.tnicacio.bd2project.entities.Venda;
import com.tnicacio.bd2project.repositories.ItensRepository;
import com.tnicacio.bd2project.repositories.ProdutoRepository;
import com.tnicacio.bd2project.repositories.VendaRepository;
import com.tnicacio.bd2project.services.exceptions.DatabaseException;

@Service
public class ItensService {

	@Autowired
	private ItensRepository repository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private VendaRepository vendaRepository;
	
	public List<Itens> findAll(){
		return repository.findAll();
	}

	public List<Itens> findAllByVendaId(Integer vid){
		return repository.findAllByIdVendaId(vid);
	}
	
	public Itens insert(Integer vid, Itens item) {
		try {
			Venda venda = vendaRepository.getOne(vid);
			Produto produto = produtoRepository.getOne(item.getProduto().getId());			
			item.setVenda(venda);
			item.setPrecoUnitario(produto.getPreco()); 
			return repository.save(item);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public List<Itens> insertAll(Integer vid, List<Itens> itens) {
		Integer pid = null;
		try {
			Venda venda = vendaRepository.getOne(vid);
			
			Set<Itens> itemSet = new HashSet<>();
			for (Itens item : itens) {
				pid = item.getProduto().getId();
				Produto produto = produtoRepository.getOne(pid);
				item.setVenda(venda);
				item.setPrecoUnitario(produto.getPreco());
				itemSet.add(item);
			}
			repository.saveAll(itemSet);
			return List.copyOf(itemSet);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

}
