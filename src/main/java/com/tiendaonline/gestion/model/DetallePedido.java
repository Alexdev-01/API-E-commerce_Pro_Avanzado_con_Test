package com.tiendaonline.gestion.model;

import java.math.BigDecimal;

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

@Entity	// Anotación para indicar que esta clase es una entidad de JPA
@Table(name = "detalle_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedido {

	@Id 		// Anotación para indicar que este campo es la clave primaria de la entidad
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// Anotación para indicar que el valor de este campo se generará automáticamente por la BBDD
	private Long id;
	
	@ManyToOne	// Anotación para indicar que esta entidad tiene una relación de muchos a uno con la entidad Pedido
	@JoinColumn (name = "pedido_id")
	private Pedido pedido;
	
	@ManyToOne	// Anotación para indicar que esta entidad tiene una relación de muchos a uno con la entidad Producto
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	private Integer cantidad;
	private BigDecimal precioUnitario;
	private BigDecimal subtotal;
	
	@PrePersist	// Anotación para indicar que este método se ejecutará antes de que la entidad se persista en la BBDD
	public void prePersist() {
		this.subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));
	}

}
