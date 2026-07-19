package com.tiendaonline.gestion.dto.producto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductoRequest {
	
	@NotBlank(message = "El nombre no es obligatorio")
	private String nombre;
	
	@NotBlank(message = "La descripción no es obligatoria")
	private String descripcion;
	
	@NotNull(message = "El precio es obligatorio")
	@Positive(message = "El precio debe ser un valor positivo")
	private BigDecimal precio;
	
	@NotNull(message = "El stock no es obligatorio")
	@Positive(message = "El stock debe ser un valor positivo")
	private Integer stock;
	
	@NotNull(message = "La categoría no es obligatoria")
	private long categoriaId;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(long categoriaId) {
		this.categoriaId = categoriaId;
	}

}
