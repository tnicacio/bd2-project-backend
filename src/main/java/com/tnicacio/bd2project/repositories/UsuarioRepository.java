package com.tnicacio.bd2project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnicacio.bd2project.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
