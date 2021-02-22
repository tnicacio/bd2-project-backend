package com.tnicacio.bd2project.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.tnicacio.bd2project.entities.Venda;
import com.tnicacio.bd2project.entities.enums.OrderStatus;
import com.tnicacio.bd2project.repositories.VendaRepository;
import com.tnicacio.bd2project.services.exceptions.DatabaseException;
import com.tnicacio.bd2project.services.exceptions.ResourceNotFoundException;

@Service
public class VendaService {

	@Autowired
	private VendaRepository repository;
	
	public List<Venda> findAll(){
		return repository.findAllByOrderByDtVendaDesc();
	}
	
	public Venda findById(Integer id) {
		Optional<Venda> venda = repository.findById(id);
		return venda.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Venda insert(Venda venda) {
		return repository.save(venda);
	}
	
	public Venda confirm(Integer id) {
		try {
			Venda venda = repository.getOne(id);
			venda.setStatus(OrderStatus.CONFIRMADA);
			return repository.save(venda);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Venda cancel(Integer id) {
		try {
			Venda venda = repository.getOne(id);
			venda.setStatus(OrderStatus.CANCELADA);
			return repository.save(venda);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
