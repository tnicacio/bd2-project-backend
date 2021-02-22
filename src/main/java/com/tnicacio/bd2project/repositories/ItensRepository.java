package com.tnicacio.bd2project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnicacio.bd2project.entities.Itens;

public interface ItensRepository extends JpaRepository<Itens, Integer>{

	public List<Itens> findAllByIdVendaId(Integer vid);
	
}
