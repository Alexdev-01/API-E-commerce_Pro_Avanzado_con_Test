package com.tiendaonline.gestion.dto.pedido;

import java.math.BigDecimal;

public class ItemPedidoResponse {
	
	private String producto;
	private Integer cantidad;
	private BigDecimal precioUnitario;
	private BigDecimal subprecio;
	
	
	public ItemPedidoResponse(String producto, Integer cantidad, BigDecimal precioUnitario, BigDecimal subprecio) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.subprecio = subprecio;
	}


	public String getProducto() {
		return producto;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}
	
	public BigDecimal getSubprecio() {
		return subprecio;
	}

}
