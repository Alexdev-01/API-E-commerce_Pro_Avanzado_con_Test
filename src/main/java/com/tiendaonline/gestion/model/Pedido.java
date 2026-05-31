package com.tiendaonline.gestion.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity	// Anotación para indicar que esta clase es una entidad de JPA
@Table(name = "pedidos")	// Anotación para especificar el nombre de la tabla en la BBDD
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
	
	@Id 		// Anotación para indicar que este campo es la clave primaria de la entidad
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// Anotación para indicar que el valor de este campo se generará automáticamente por la BBDD
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	private LocalDateTime fecha;
	private BigDecimal total;
	
	@Enumerated(EnumType.STRING)	// Anotación para indicar que el valor de este campo se almacenará como una cadena en la BBDD
	private EstadoPedido estado;
	
	@OneToMany(mappedBy = "pedido", cascade =CascadeType.ALL, orphanRemoval = true)	// Anotación para indicar que esta entidad tiene una relación de uno a muchos con la entidad DeatellePedido
	public List<DetallePedido> detalles = new ArrayList<>();
	
	@PrePersist	// Anotación para indicar que este método se ejecutará antes de que la entidad se persista en la BBDD
	public void prePersist() {
		this.fecha = LocalDateTime.now();
		this.estado = EstadoPedido.CREADO;
	}
	
    public void addDetalle(DetallePedido detalle) {
        detalles.add(detalle);
        detalle.setPedido(this);
    }
	
	

}
