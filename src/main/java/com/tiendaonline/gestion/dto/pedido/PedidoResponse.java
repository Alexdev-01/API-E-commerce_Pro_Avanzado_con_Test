package com.tiendaonline.gestion.dto.pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponse {
	
	private Long id;
	private LocalDateTime fecha;
	private BigDecimal total;
	private String usuario;
	private List<ItemPedidoResponse> items;
	
	
	public PedidoResponse(Long id, LocalDateTime fecha, BigDecimal total, String usuario,
			List<ItemPedidoResponse> items) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.total = total;
		this.usuario = usuario;
		this.items = items;
	}


	public Long getId() {
		return id;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public List<ItemPedidoResponse> getItems() {
		return items;
	}


}
