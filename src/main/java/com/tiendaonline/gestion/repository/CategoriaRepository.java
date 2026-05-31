package com.tiendaonline.gestion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tiendaonline.gestion.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	Optional<Categoria> findByNombre(String nombre);

}
