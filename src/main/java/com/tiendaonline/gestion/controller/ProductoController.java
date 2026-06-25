package com.tiendaonline.gestion.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

import com.tiendaonline.gestion.dto.common.ApiResponse;
import com.tiendaonline.gestion.dto.producto.ProductoRequest;
import com.tiendaonline.gestion.dto.producto.ProductoResponse;
import com.tiendaonline.gestion.model.Producto;
import com.tiendaonline.gestion.service.ProductoService;

import jakarta.validation.Valid;

@RestController  // Anotación para indicar que esta clase es un controlador REST
@RequestMapping("/productos")  // Ruta base para las operaciones relacionadas con productos
public class ProductoController {
	
	public final ProductoService productoService;

	public ProductoController(ProductoService productoService) {
		super();
		this.productoService = productoService;
	}
	
	//Acceso Admin
	@PostMapping
	public ResponseEntity<Producto> crearProducto(@Valid @RequestBody ProductoRequest request) {
		return ResponseEntity.ok(productoService.crearProducto(request));
	}
	
	//Acceso public
	@GetMapping
	public ResponseEntity<List<ProductoResponse>> listarProductos() {
		return ResponseEntity.ok(productoService.listarTodos());
	}
	
	//Acceso public
	@GetMapping("/{id}")
	public ResponseEntity<ProductoResponse> obtenerproductos(@PathVariable Long id) {
		return ResponseEntity.ok(productoService.obtenerPorId(id));
	}
	
	//Acceso Admin
	@PutMapping("/{id}")
	public ResponseEntity<Producto> actualizarProductos(@PathVariable Long id, @RequestBody Producto producto) {
		return ResponseEntity.ok(productoService.actualizarProducto(id, producto));
	}
	
	//Acceso Admin
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {	
		productoService.eliminarProducto(id);
		return ResponseEntity.noContent().build();
	}
	
	//Acceso public
	@GetMapping("/paginado")
	public ResponseEntity<ApiResponse<Page<ProductoResponse>>> listarProductos(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		
		Page<ProductoResponse> productos = productoService.listarProductosPaginados(page, size);
		
		return ResponseEntity.ok(new ApiResponse<>(true,"Productos obtenidos correctamente",productos));
	
	}
	
}