package com.tnicacio.bd2project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tnicacio.bd2project.entities.Venda;
import com.tnicacio.bd2project.repositories.VendaRepository;
import com.tnicacio.bd2project.services.exceptions.ResourceNotFoundException;

@Service
public class VendaService {

	@Autowired
	private VendaRepository repository;
	
	public List<Venda> findAll(){
		return repository.findAll();
	}
	
	public Venda findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
}
