package com.tnicacio.bd2project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tnicacio.bd2project.entities.Venda;
import com.tnicacio.bd2project.services.VendaService;

@RestController
@RequestMapping(value = "/vendas")
public class VendaController {

	@Autowired
	private VendaService service;
	
	@GetMapping
	public ResponseEntity<List<Venda>> findAll() {
		List<Venda> produtos = service.findAll();
		return ResponseEntity.ok().body(produtos);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Venda> findById(@PathVariable Integer id) {
		Venda produto = service.findById(id);
		return ResponseEntity.ok().body(produto);
	}
}
