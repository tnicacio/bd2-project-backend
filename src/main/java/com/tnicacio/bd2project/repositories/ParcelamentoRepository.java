package com.tnicacio.bd2project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnicacio.bd2project.entities.Parcelamento;

public interface ParcelamentoRepository extends JpaRepository<Parcelamento, Integer>{

	public List<Parcelamento> findAllByVendaId(Integer vid);
	
}
