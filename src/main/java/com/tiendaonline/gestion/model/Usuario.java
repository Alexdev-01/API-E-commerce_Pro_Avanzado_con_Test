package com.tiendaonline.gestion.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity	// Indica que esta clase es una entidad JPA y se mapeará a una tabla en la BBDD
@Table(name = "usuarios")	// Especifica el nombre de la tabla en la base de datos a la que se mapeará esta entidad
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	
	@Id	// Indica que este campo es la clave primaria de la entidad
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// Especifica que el valor de este campo se generará automáticamente por la BBDD (auto-incremental)
	private Long id;
	
	@Column(nullable = false, unique = true)	// Especifica que esta columna no puede ser nula y debe ser única en la BBDD
	private String username;
	
	@Column(nullable = false, unique = true)	// Especifica que esta columna no puede ser nula y debe ser única en la BBDD
	private String email;
	
	@Column(nullable = false)	// Especifica que esta columna no puede ser nula en la BBDD
	private String password;
	
	@Enumerated(EnumType.STRING)	// Especifica que este campo es un enumerado y se almacenará como una cadena en la BBDD
	@Column(name = "rol", nullable = false)
	private Rol role;
	
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "usuario")	// Especifica una relación uno a muchos con la entidad Pedido, donde "usuario" es el campo en la clase Pedido que mapea esta relación
	private List<Pedido> pedidos = new ArrayList<>();
	
	@PrePersist	// Especifica que este método se ejecutará antes de que la entidad sea persistida en la BBDD, para establecer la fecha de creación
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
	}
	
	

}
