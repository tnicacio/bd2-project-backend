package com.tnicacio.bd2project.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tnicacio.bd2project.entities.Produto;
import com.tnicacio.bd2project.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> produtos = service.findAll();
		return ResponseEntity.ok().body(produtos);
	}

	@GetMapping(value = "/active")
	public ResponseEntity<List<Produto>> findActives() {
		List<Produto> produtos = service.findActives();
		return ResponseEntity.ok().body(produtos);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		Produto produto = service.findById(id);
		return ResponseEntity.ok().body(produto);
	}

	@PostMapping(value = "/userid={id}")
	public ResponseEntity<Produto> insert(@RequestBody Produto produto, @PathVariable Integer id) {
		produto = service.insert(produto, id);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(produto);
	}

	@DeleteMapping(value = "/{id}/userid={uid}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id,
			@PathVariable(value = "uid") Integer uid) {
		service.delete(id, uid);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}/userid={uid}")
	public ResponseEntity<Produto> update(@PathVariable(value = "id") Integer id, @RequestBody Produto obj,
			@PathVariable(value = "uid") Integer uid) {
		obj = service.update(id, obj, uid);
		return ResponseEntity.ok().body(obj);
	}
}
