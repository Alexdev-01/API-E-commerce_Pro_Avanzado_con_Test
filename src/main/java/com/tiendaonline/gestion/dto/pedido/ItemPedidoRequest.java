package com.tiendaonline.gestion.dto.pedido;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ItemPedidoRequest {
	
	@NotNull(message = "El producto es obligatorio")
	private Long productoId;
	
	@NotNull(message = "La cantidad es obligatoria")
	@Min(value = 1, message = "La cantidad debe ser mayor de 0")
	private Integer cantidad;
	
	
	
	public ItemPedidoRequest() {
		super();
	}

	public Long getProductoId() {
		return productoId;
	}
	
	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
