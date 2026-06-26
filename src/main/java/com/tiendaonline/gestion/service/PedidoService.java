package com.tiendaonline.gestion.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tiendaonline.gestion.dto.pedido.CrearPedidoRequest;
import com.tiendaonline.gestion.dto.pedido.PedidoResponse;
import com.tiendaonline.gestion.model.Pedido;
import com.tiendaonline.gestion.model.Usuario;

public interface PedidoService {
	
	Pedido crearPedido(Pedido pedido);
	
	List<PedidoResponse> obtenerPedidosUsuario(Usuario usuario);
	
	List<PedidoResponse> obtenerPedidosUsuario(String username);
	
	List<PedidoResponse> obtenerTodos();
	
	ResponseEntity<PedidoResponse> crearPedido(CrearPedidoRequest request, String username);
	
	PedidoResponse obtenerPedidoPorId(Long id);
	
	PedidoResponse obtenerPedidoPorId(Long id, String username);

	List<PedidoResponse> obtenerTodosLosPedidos();
	
}
