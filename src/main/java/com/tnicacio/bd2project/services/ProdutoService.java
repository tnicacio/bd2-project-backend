package com.tnicacio.bd2project.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.tnicacio.bd2project.entities.Produto;
import com.tnicacio.bd2project.entities.Usuario;
import com.tnicacio.bd2project.repositories.ProdutoRepository;
import com.tnicacio.bd2project.repositories.UsuarioRepository;
import com.tnicacio.bd2project.services.exceptions.DatabaseException;
import com.tnicacio.bd2project.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private UsuarioRepository userRepository;

	public List<Produto> findAll() {
		return repository.findAllByOrderByDescricaoAsc();
	}

	public List<Produto> findActives() {
		return repository.findAllByDtInativacaoIsNullOrderByDescricaoAsc();
	}

	public Produto findById(Integer id) {
		Optional<Produto> produto = repository.findById(id);
		return produto.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Produto insert(Produto produto, Integer userId) {
		Usuario user = userRepository.getOne(userId);
		produto.setUsuario(user);
		return repository.save(produto);
	}

	public void delete(Integer id, Integer userId) {
		try {
			// repository.deleteById(id);
			Produto produto = repository.getOne(id);
			Usuario user = userRepository.getOne(userId);
			produto.setUsuario(user);
			produto.setIeAtivo(false);
			repository.save(produto);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Produto update(Integer id, Produto obj, Integer userId) {
		try {
			Produto produto = repository.getOne(id);
			Usuario user = userRepository.getOne(userId);

			if (produto != null) {
				produto.setUsuario(user);
				updateData(produto, obj);
				return repository.save(produto);
			}
			return null;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	private void updateData(Produto entity, Produto obj) {
		if (entity.getUsuario() == null) {
			throw new DatabaseException("Missing User ID");
		}
		if (obj.getDescricao() != null && !"".equals(obj.getDescricao().trim())) {
			entity.setDescricao(obj.getDescricao());
		}
		if (obj.getImageUri() != null && !"".equals(obj.getImageUri().trim())) {
			entity.setImageUri(obj.getImageUri());
		}
		if (obj.getPreco() != null && obj.getPreco() > 0.0) {
			entity.setPreco(obj.getPreco());
		}
		if (obj.getQtEstoque() != null && obj.getQtEstoque() > 0.0) {
			entity.setQtEstoque(obj.getQtEstoque());
		}
		if (obj.getQtEstoqueMinimo() != null && obj.getQtEstoqueMinimo() > 0.0) {
			entity.setQtEstoqueMinimo(obj.getQtEstoqueMinimo());
		}
		if (obj.getQtReservada() != null && obj.getQtReservada() > 0.0) {
			entity.setQtReservada(obj.getQtReservada());
		}
		if (obj.getIeAtivo() != null) {
			entity.setIeAtivo(obj.getIeAtivo());
		}
	}
}
