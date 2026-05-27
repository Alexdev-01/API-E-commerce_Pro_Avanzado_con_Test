package com.tiendaonline.gestion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendaonline.gestion.dto.pedido.CrearPedidoRequest;
import com.tiendaonline.gestion.dto.pedido.PedidoResponse;
import com.tiendaonline.gestion.model.Pedido;
import com.tiendaonline.gestion.service.PedidoService;

import jakarta.validation.Valid;

@RestController	// Marca esta clase como un controlador REST en Spring
@RequestMapping("/pedidos")	// Define la ruta base para todas las solicitudes relacionadas con pedidos
// Controlador que maneja las solicitudes HTTP relacionadas con los pedidos
public class PedidoController {
	
	private final PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {
		super();
		this.pedidoService = pedidoService;
	}
	
	@PostMapping	// Define un endpoint POST para crear un nuevo pedido
	public ResponseEntity<PedidoResponse> crearPedido(@Valid @RequestBody CrearPedidoRequest request, Authentication authentication) {
		
		String username = authentication.getName(); 
		
		return this.pedidoService.crearPedido(request, username);
	}

}