package com.tiendaonline.gestion.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity	// Indica que esta clase es una entidad JPA y se mapeará a una tabla en la BBDD
@Table(name = "categorias")	// Especifica el nombre de la tabla en la BBDD a la que se mapeará esta entidad
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

	@Id 	// Indica que este campo es la clave primaria de la entidad
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// Especifica que el valor de este campo se generará automáticamente por la BBDD (auto-incremental)
	private Long id;
	
	@Column(nullable = false, unique = true)	// Especifica que esta columna no puede ser nula y debe ser única en la BBDD
	private String nombre;
	
	private String descripcion;
	
	@JsonIgnore
	@OneToMany(mappedBy = "categoria")	// Especifica una relación uno a muchos con la entidad Producto, donde "categoria" es el campo en la clase Producto que mapea esta relación
	private List<Producto> productos;
}
