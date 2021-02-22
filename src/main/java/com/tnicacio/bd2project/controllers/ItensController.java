package com.tnicacio.bd2project.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tnicacio.bd2project.entities.Itens;
import com.tnicacio.bd2project.services.ItensService;

@RestController
@RequestMapping(value = "/itens")
public class ItensController {

	@Autowired
	private ItensService service;

	@GetMapping(value = "/{vid}")
	public ResponseEntity<List<Itens>> findByVendaId(@PathVariable Integer vid) {
		List<Itens> itens = service.findAllByVendaId(vid);
		return ResponseEntity.ok().body(itens);
	}

	@PostMapping(value = "/{vid}")
	public ResponseEntity<Itens> insert(@PathVariable Integer vid, @RequestBody Itens item) {
		item = service.insert(vid, item);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/venda={vid}")
				.buildAndExpand(vid).toUri();
		return ResponseEntity.created(uri).body(item);
	}
	
	@PostMapping(value = "/{vid}/postall")
	public ResponseEntity<List<Itens>> insert(@PathVariable Integer vid, @RequestBody List<Itens> itens) {
		itens = service.insertAll(vid, itens);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/venda={id}")
				.buildAndExpand(itens.get(0).getVenda().getId()).toUri();
		return ResponseEntity.created(uri).body(itens);
	}

}
