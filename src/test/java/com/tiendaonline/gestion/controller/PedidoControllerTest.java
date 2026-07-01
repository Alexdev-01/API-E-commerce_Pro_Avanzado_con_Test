package com.tiendaonline.gestion.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tiendaonline.gestion.dto.pedido.CrearPedidoRequest;
import com.tiendaonline.gestion.dto.pedido.PedidoResponse;
import com.tiendaonline.gestion.service.PedidoService;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.security.test.context.support.WithMockUser;


@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private PedidoService pedidoService;
	
	
	@WithMockUser(username = "cliente1", roles = "CLIENTE")
	@Test
	void deberiaCrearPedido() throws Exception {
		
		PedidoResponse response = new PedidoResponse();
		
		when(pedidoService.crearPedido(
				any(CrearPedidoRequest.class),
				anyString()))
				.thenReturn(ResponseEntity.ok(response));
		
		mockMvc.perform(post("/pedidos")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
				{
					"items":[
					{
						"productoId":1,
						"cantidad":2
					}
					]
				}
				"""))
				.andExpect(status().isOk());
	}
	
	
	@WithMockUser(username = "cliente1", roles = "CLIENTE")
	@Test
	void deberiaObtenerMisPedidos() throws Exception {
		
		List<PedidoResponse> pedidos = List.of(new PedidoResponse());
		
		when(pedidoService.obtenerPedidosUsuario("cliente1"))
				.thenReturn(pedidos);
		
		mockMvc.perform(get("/pedidos"))
				.andExpect(status().isOk());
	}
	
	
	@WithMockUser(username = "cliente1", roles = "CLIENTE")
	@Test
	void deberiaObtenerPedidoPorId() throws Exception {
		
		PedidoResponse pedido = new PedidoResponse();
		
		when(pedidoService.obtenerPedidoPorId(1L, "cliente1"))
				.thenReturn(pedido);
		
		mockMvc.perform(get("/pedidos/1"))
				.andExpect(status().isOk());
	}
	
	//ADMIN
	@WithMockUser(username = "admin", roles = "ADMIN")
	@Test
	void deberiaObtenerTodosLosPedidos() throws Exception {
		
		List<PedidoResponse> pedidos = List.of(new PedidoResponse());
		
		when(pedidoService.obtenerTodosLosPedidos())
				.thenReturn(pedidos);
		
		mockMvc.perform(get("/pedidos/admin"))
				.andExpect(status().isOk());
	}
	
	
	@WithMockUser(username = "cliente1", roles = "CLIENTE")
	@Test
	void clienteNoDebeAccederATodosLosPedidos() throws Exception {
		
		mockMvc.perform(get("/pedidos/admin"))
				.andExpect(status().isForbidden());
	}
	
}
