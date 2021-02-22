package com.tnicacio.bd2project.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.tnicacio.bd2project.entities.Usuario;
import com.tnicacio.bd2project.entities.enums.UserRole;
import com.tnicacio.bd2project.repositories.UsuarioRepository;
import com.tnicacio.bd2project.services.exceptions.DatabaseException;
import com.tnicacio.bd2project.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public List<Usuario> findAll() {
		return repository.findAllByOrderByNomeAsc();
	}

	public List<Usuario> findActive() {
		return repository.findAllByDtInativacaoIsNullOrderByNomeAsc();
	}

	public Usuario findById(Integer id) {
		Optional<Usuario> usuario = repository.findById(id);
		return usuario.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Usuario insert(Usuario usuario) {
		return repository.save(usuario);
	}
	
	public void inactivate(Integer id) {
		try {
			Usuario user = repository.getOne(id);
			user.setIeAtivo(false);
			repository.save(user);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * Deprecated: use inactivate instead.
	 */
	public void delete(Integer id) {
		try {
			Usuario user = repository.getOne(id);
			user.setIeAtivo(false);
			repository.save(user);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Usuario update(Integer id, Usuario obj) {
		try {
			Usuario user = repository.getOne(id);
			if (obj.getCargo() == null) {
				obj.setCargo(UserRole.CLIENT);	
			}
			updateData(user, obj);
			return repository.save(user);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	private void updateData(Usuario entity, Usuario obj) {
		if (entity.getId() == null) {
			throw new DatabaseException("Missing User ID");
		}
		if (obj.getNome() != null && !"".equals(obj.getNome().trim())) {
			entity.setNome(obj.getNome());
		}
		if (obj.getImageUri() != null && !"".equals(obj.getImageUri().trim())) {
			entity.setImageUri(obj.getImageUri());
		}
		if (obj.getEmail() != null && !"".equals(obj.getEmail().trim())) {
			entity.setEmail(obj.getEmail());
		}
		if (obj.getSenha() != null && !"".equals(obj.getSenha().trim())) {
			entity.setSenha(obj.getSenha());
		}
		if (obj.getCargo() != null && obj.getCargo().getCode() > 0) {
			entity.setCargo(obj.getCargo());
		}
		if (obj.getIeAtivo() != null) {
			entity.setIeAtivo(obj.getIeAtivo());
		}
	}

}
