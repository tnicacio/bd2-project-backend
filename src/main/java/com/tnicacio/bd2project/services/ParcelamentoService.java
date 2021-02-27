package com.tnicacio.bd2project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tnicacio.bd2project.entities.Parcelamento;
import com.tnicacio.bd2project.repositories.ParcelamentoRepository;

@Service
public class ParcelamentoService {

	@Autowired
	private ParcelamentoRepository repository;
	
	public List<Parcelamento> findAll(){
		return repository.findAll();
	}

	public List<Parcelamento> findAllByVendaId(Integer vid){
		return repository.findAllByVendaId(vid);
	}
	
	
}
