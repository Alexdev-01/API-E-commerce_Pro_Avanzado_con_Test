package com.tiendaonline.gestion.dto.producto;

import java.math.BigDecimal;

public class ProductoResponse {
	
	private Long id;
	private String nombre;
	private String descripcion;
	private BigDecimal precio;
	private Integer stock;
	private String categoriaNombre;
	
	
	public ProductoResponse(Long id, String nombre, String descripcion, BigDecimal precio, Integer stock,
			String categoriaNombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.categoriaNombre = categoriaNombre;
	}
	
	public ProductoResponse() {
		super();
	}


	public Long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public Integer getStock() {
		return stock;
	}
	public String getCategoriaNombre() {
		return categoriaNombre;
	}
	
	
	

}
