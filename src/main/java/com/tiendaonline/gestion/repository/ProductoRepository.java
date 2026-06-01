package com.tiendaonline.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.tiendaonline.gestion.model.Producto;

public interface ProductoRepository  extends JpaRepository<Producto, Long>, JpaSpecificationExecutor<Producto> {

	Page<Producto> findByActivoTrue(Pageable pageable);
	
	
	
	@Query("""
			SELECT p FROM Producto p
			WHERE p.activo = true
			AND (:categoriaId IS NULL OR p.categoria.id = :categoriaId)
			AND (:precioMin IS NULL OR p.precio >= :precioMin)
			AND (:precioMax IS NULL OR p.precio <= :precioMax)
	""")
	
	Page<Producto> filtrarProductos(
			@Param("categoriaID") Long categoriaID,
			@Param("precioMin") BigDecimal precioMin,
			@Param("precioMax") BigDecimal precioMax,
			Pageable pageable);
	
}
