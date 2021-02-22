package com.tnicacio.bd2project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnicacio.bd2project.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	public List<Usuario> findAllByOrderByNomeAsc();
	
	public List<Usuario> findAllByDtInativacaoIsNullOrderByNomeAsc();
}
