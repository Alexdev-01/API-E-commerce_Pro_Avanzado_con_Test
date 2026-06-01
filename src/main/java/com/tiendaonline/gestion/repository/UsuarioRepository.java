package com.tiendaonline.gestion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendaonline.gestion.model.Usuario;

// Interfaz que extiende JpaRepository para proporcionar métodos CRUD para la entidad Usuario
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByUsername(String username);		// Buscar un usuario por su nombre
	Optional<Usuario> findByEmail(String email);	//Buscar un usuario por su email
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);

}
