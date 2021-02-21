package com.tnicacio.bd2project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tnicacio.bd2project.entities.Usuario;
import com.tnicacio.bd2project.repositories.UsuarioRepository;
import com.tnicacio.bd2project.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> findAll(){
		return repository.findAllByOrderByNomeAsc();
	}
	
	public Usuario findById(Integer id) {
		Optional<Usuario> usuario = repository.findById(id);
		return usuario.orElseThrow(() -> new ResourceNotFoundException(id));
	}
}
