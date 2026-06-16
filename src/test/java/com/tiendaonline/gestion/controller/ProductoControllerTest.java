package com.tiendaonline.gestion.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.tiendaonline.gestion.dto.producto.ProductoResponse;
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
		
		Page<ProductoResponse> pagina = new PageImpl<>(Collections.emptyList());
		
		when(productoService.listarProductosPaginados(0,5)).thenReturn(pagina);
		
	    mockMvc.perform(get("/productos"))
	    .andExpect(status().isOk());
	}

}
