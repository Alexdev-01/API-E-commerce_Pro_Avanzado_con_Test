package com.tiendaonline.gestion.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity	// Indica que esta clase es una entidad JPA y se mapeará a una tabla en la BBDD
@Table(name = "productos")	// Especifica el nombre de la tabla en la BBDD a la que se mapeará esta entidad
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
	
	@Id	// Indica que este campo es la clave primaria de la entidad
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// Especifica que el valor de este campo se generará automáticamente por la BBDD (auto-incremental)
	private Long id;
	
	@Column(nullable = false)	// Especifica que esta columna no puede ser nula en la BBDD
	private String nombre;
	
	private String descripcion;
	
	@Column(nullable = false)	// Especifica que esta columna no puede ser nula en la BBDD
	private BigDecimal precio;
	
	@Column(nullable = false)	// Especifica que esta columna no puede ser nula en la BBDD
	private Integer stock;
	
	private Boolean activo = true;
	
	@ManyToOne	// Especifica una relación muchos a uno con la entidad Categoria, donde "categoria" es el campo en la clase Producto que mapea esta relación
	@JoinColumn(name = "categoria_id")	
	private Categoria categoria;
	
	private LocalDateTime createdAt;
	
	@PrePersist	// Especifica que este método se ejecutará antes de que la entidad sea persistida en la BBDD, para establecer la fecha de creación
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
	}

}
