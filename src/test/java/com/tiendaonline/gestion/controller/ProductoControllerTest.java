package com.tiendaonline.gestion.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.tiendaonline.gestion.dto.producto.ProductoRequest;
import com.tiendaonline.gestion.dto.producto.ProductoResponse;
import com.tiendaonline.gestion.exception.ResourceNotFoundException;
import com.tiendaonline.gestion.model.Producto;
import com.tiendaonline.gestion.service.ProductoService;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private ProductoService productoService;
	
	
	// Test para verificar que el endpoint de listar productos devuelve un estado HTTP 200 OK
	@Test
	void deberiaListarProductos() throws Exception {
		
		List<ProductoResponse> productos = List.of(new ProductoResponse());
		
		when(productoService.listarTodos()).thenReturn(productos);

		mockMvc.perform(get("/productos"))
		.andExpect(status().isOk());
	}
	
	// Test para verificar que el endpoint de obtener producto por ID devuelve un estado HTTP 200 OK y los datos correctos
	@Test
	void deberiaObtenerProductoPorId() throws Exception {
		
		ProductoResponse producto = new ProductoResponse();
		producto.setId(1L);
		producto.setNombre("Laptop");
		
		when(productoService.obtenerPorId(1L))
			.thenReturn(producto);
		
		mockMvc.perform(get("/productos/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.nombre").value("Laptop"));
	}
	
	// Test para verificar que el endpoint de listar productos paginados devuelve un estado HTTP 200 OK
	@Test
	void deberiaListarProductosPaginados() throws Exception {
		
		Page<ProductoResponse> pagina =new PageImpl<>(Collections.emptyList());
		
		when(productoService.listarProductosPaginados(0,5)).thenReturn(pagina);
		
		mockMvc.perform(get("/productos/paginado")
				.param("page", "0")
				.param("size", "5")
		)
		.andExpect(status().isOk());
	}
	
	// Test para verificar que el endpoint de crear producto devuelve un estado HTTP 200 OK
	@Test
	void deberiaCrearProducto() throws Exception {
		
		Producto producto = new Producto();
		producto.setId(1L);
		producto.setNombre("Laptop");
		
		when(productoService.crearProducto(any(ProductoRequest.class))).thenReturn(producto);
		
		mockMvc.perform(post("/productos")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
							"nombre":"Laptop",
							"descripcion":"Laptop Gaming",
							"precio":1200,
							"stock":10,
							"categoriaId":1
						}
						"""))
							.andExpect(status().isOk());
	}
	
	// Test para verificar que el endpoint de crear producto devuelve un estado HTTP 400 Bad Request cuando el nombre del producto está vacío
	@Test
	void deberiaRetornarBadRequestCuandoNombreEsVacio() {
		
		mockMvc.perform(post("/productos")
			.contentType(MediaType.APPLICATION_JSON)
			.content("""
					{
						"nombre":"",
						"descripcion":"Laptop Gaming",
						"precio":1200,
						"stock":10,
						"categoriaId":1
					}
					"""))
			.andExpect(status().isBadRequest());
	}
	
	// Test para verificar que el endpoint de obtener producto por ID devuelve un estado HTTP 404 Not Found cuando el producto no existe
	@Test
	void deberiaRetornar404CuandoProductoNoExiste() throws Exception {
		
		when(productoService.obtenerPorId(999L)).thenThrow(new ResourceNotFoundException("Producto no encontrado"));
		
		mockMvc.perform(get("/productos/999"))
			.andExpect(status().isNotFound());
	}
	
	// Test para verificar que el endpoint de actualizar producto devuelve un estado HTTP 200 OK
	@Test
	void deberiaActualizarProducto() throws Exception {
		
		ProductoResponse response = new ProductoResponse();
		response.setId(1L);
		response.setNombre("Laptop Actualizada");
		
		when(productoService.actualizarProducto(
				eq(1L),
				any(ProductoRequest.class)))
		.thenReturn(response);
		
		mockMvc.perform(put("/productos/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
							"nombre":"Laptop Actualizada",
							"descripcion":"Nueva",
							"precio":1500,
							"stock":5,
							"categoriaId":1
						}
						"""))
		.andExpect(status().isOk());
	}
	
	// Test para verificar que el endpoint de eliminar producto devuelve un estado HTTP 200 OK
	@Test
	void deberiaEliminarProducto() throws Exception {
		
		doNothing().when(productoService).eliminarProducto(1L);
		
		mockMvc.perform(delete("/productos/1"))
			.andExpect(status().isOk());
	}
	
	
	
	
}
