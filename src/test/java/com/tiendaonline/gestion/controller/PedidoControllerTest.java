package com.tiendaonline.gestion.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
	
}
