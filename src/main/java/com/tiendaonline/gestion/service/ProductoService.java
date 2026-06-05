package com.tiendaonline.gestion.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.tiendaonline.gestion.dto.producto.ProductoRequest;
import com.tiendaonline.gestion.dto.producto.ProductoResponse;
import com.tiendaonline.gestion.model.Producto;

public interface ProductoService {
	
	Producto crearProducto(ProductoRequest request);
	
	Producto actualizarProducto(Long id, Producto producto);
	
	void eliminarProducto(Long id);
	
	ProductoResponse obtenerPorId(Long id);
	
	List<ProductoResponse> listarTodos();
	
	Page<ProductoResponse> listarProductosPaginados(int page, int size);
	
	Page<ProductoResponse> filtrarPrductos(Long categoriaId, BigDecimal precioMin, BigDecimal precioMax, int page, int size);

}