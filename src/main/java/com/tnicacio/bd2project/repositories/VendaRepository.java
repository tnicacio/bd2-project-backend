package com.tnicacio.bd2project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnicacio.bd2project.entities.Venda;

public interface VendaRepository extends JpaRepository<Venda, Integer>{

	public List<Venda> findAllByOrderByDtVendaDesc();
}
