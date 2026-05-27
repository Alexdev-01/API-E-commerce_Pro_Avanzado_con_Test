package com.tiendaonline.gestion.dto.pedido;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public class CrearPedidoRequest {
	
	@NotEmpty(message = "La lista debe tener al menos un producto")
	@Valid	// Valida cada elemento de la lista
	private List<ItemPedidoRequest> items;	
	
	
	public List<ItemPedidoRequest> getItems() {
		return items;
	}
	
	public void setItems (List<ItemPedidoRequest> items) {
		this.items = items;
	}

}
