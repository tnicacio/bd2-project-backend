package com.tnicacio.bd2project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tnicacio.bd2project.entities.Parcelamento;
import com.tnicacio.bd2project.services.ParcelamentoService;

@RestController
@RequestMapping(value = "/parcelamentos")
public class ParcelamentoController {

	@Autowired
	private ParcelamentoService service;
	
	@GetMapping()
	public ResponseEntity<List<Parcelamento>> findAll() {
		List<Parcelamento> itens = service.findAll();
		return ResponseEntity.ok().body(itens);
	}

	@GetMapping(value = "/{vid}")
	public ResponseEntity<List<Parcelamento>> findByVendaId(@PathVariable Integer vid) {
		List<Parcelamento> itens = service.findAllByVendaId(vid);
		return ResponseEntity.ok().body(itens);
	}
}
