package com.tiendaonline.gestion.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tiendaonline.gestion.dto.pedido.CrearPedidoRequest;
import com.tiendaonline.gestion.dto.pedido.ItemPedidoRequest;
import com.tiendaonline.gestion.dto.pedido.ItemPedidoResponse;
import com.tiendaonline.gestion.dto.pedido.PedidoResponse;
import com.tiendaonline.gestion.exception.BadRequestException;
import com.tiendaonline.gestion.exception.ResourceNotFoundException;
import com.tiendaonline.gestion.exception.StockInsuficienteException;
import com.tiendaonline.gestion.model.DetallePedido;
import com.tiendaonline.gestion.model.Pedido;
import com.tiendaonline.gestion.model.Producto;
import com.tiendaonline.gestion.model.Usuario;
import com.tiendaonline.gestion.repository.PedidoRepository;
import com.tiendaonline.gestion.repository.ProductoRepository;
import com.tiendaonline.gestion.repository.UsuarioRepository;
import com.tiendaonline.gestion.service.PedidoService;


import jakarta.transaction.Transactional;

//Implementación de la interfaz PedidoService, que maneja la lógica de negocio relacionada con los pedidos
@Service	// Marca esta clase como un componente de servicio en Spring
public class PedidoServiceImpl implements PedidoService {
	
	private final PedidoRepository pedidoRepository;
	private final ProductoRepository productoRepository;
	private final UsuarioRepository usuarioRepository;
	
	public PedidoServiceImpl(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository) {
		this.pedidoRepository = pedidoRepository;
		this.productoRepository = productoRepository;
		this.usuarioRepository = usuarioRepository;
	}
	
	private static final Logger log = LoggerFactory.getLogger(PedidoServiceImpl.class);
	
	
	@Override
	public Pedido crearPedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Override
	public List<PedidoResponse> obtenerPedidosUsuario(Usuario usuario) {
		return pedidoRepository.findByUsuario(usuario) .stream().map(this::mapToResponse).toList();
	}

	@Override
	public List<PedidoResponse> obtenerTodos() {
		return pedidoRepository.findAll().stream().map(this::mapToResponse).toList();
	}


	@Override
	@Transactional	// Asegura que todas las operaciones dentro de este método se ejecuten en una sola transacción
	// Método para crear un nuevo pedido a partir de una solicitud y el nombre de usuario del cliente
	public ResponseEntity<PedidoResponse> crearPedido(CrearPedidoRequest request, String username) {
		
		//valida extra
		if (request.getItems() == null || request.getItems().isEmpty()) {
			throw new BadRequestException("El pedido debe tener al menos un producto");
		}

		Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow();
		
		Pedido pedido = new Pedido();
		pedido.setUsuario(usuario);
		
		BigDecimal total = BigDecimal.ZERO;
		
		log.info("Iniciando creación de pedido para usuario: {}", usuario.getUsername());
		
		for (ItemPedidoRequest item : request.getItems()) {
			log.info("Procesando producto ID: {} - Cantidad: {}", item.getProductoId(), item.getCantidad());
			Producto producto = productoRepository.findById(item.getProductoId()).orElseThrow(() -> {
				log.error("Producto no encontrado ID: {}", item.getProductoId());
				return new ResourceNotFoundException("Producto no encontrado");
			});
			
			if (producto.getStock() < item.getCantidad()) {
				log.warn("Stock insuficiente para producto ID: {}. Stock actual: {}, solicitado: {}",producto.getId(),producto.getStock(),item.getCantidad());
				throw new StockInsuficienteException("Stock insuficiente para: " + producto.getNombre());
			}
			
			//Reducir Stock del producto
			producto.setStock(producto.getStock() - item.getCantidad());
			
			log.info("Stock actualizado para producto ID: {}. Nuevo stock: {}",producto.getId(),producto.getStock());
			
			DetallePedido detalle = new DetallePedido();
			
			detalle.setProducto(producto);
			detalle.setCantidad(item.getCantidad());
			detalle.setPrecioUnitario(producto.getPrecio());
			
			detalle.setSubtotal(producto.getPrecio().multiply(BigDecimal.valueOf(item.getCantidad())));
			
			pedido.addDetalle(detalle);
			
			total = total.add(detalle.getSubtotal());
			
			}
		
		pedido.setTotal(total);
		
		log.info("Total del pedido calculado: {}", total);
		Pedido pedidoGuardado = pedidoRepository.save(pedido);
		log.info("Pedido creado correctamente. ID Pedido: {} - Usuario: {}",pedido.getId(),usuario.getUsername());
		return ResponseEntity.ok(mapToResponse(pedidoGuardado));
	}
	
	// Método para obtener un pedido por su ID y mapearlo a una respuesta DTO
	private PedidoResponse mapToResponse(Pedido pedido) {

		List<ItemPedidoResponse> items = pedido.getDetalles()
				.stream()
				.map(detalle -> new ItemPedidoResponse(
						detalle.getProducto().getNombre(),
						detalle.getCantidad(),
						detalle.getPrecioUnitario(),
						detalle.getSubtotal()
						))
				.toList();
		
		return new PedidoResponse(
				pedido.getId(),
				pedido.getFecha(),
				pedido.getTotal(),
				pedido.getUsuario().getUsername(),
				items
				);
	}
	

}
