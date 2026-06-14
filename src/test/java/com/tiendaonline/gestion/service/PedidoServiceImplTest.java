package com.tiendaonline.gestion.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tiendaonline.gestion.dto.pedido.CrearPedidoRequest;
import com.tiendaonline.gestion.dto.pedido.ItemPedidoRequest;
import com.tiendaonline.gestion.exception.StockInsuficienteException;
import com.tiendaonline.gestion.model.Pedido;
import com.tiendaonline.gestion.model.Producto;
import com.tiendaonline.gestion.model.Usuario;
import com.tiendaonline.gestion.repository.PedidoRepository;
import com.tiendaonline.gestion.repository.ProductoRepository;
import com.tiendaonline.gestion.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class) // Anotación de JUnit 5 para habilitar la extensión de Mockito en esta clase de prueba
public class PedidoServiceImplTest {
	
	
	@Mock // Anotación de Mockito para crear un mock del repositorio de pedidos. Crea un repositorio falso.
	private PedidoRepository pedidoRepository;
	
	@Mock
	private ProductoRepository productoRepository;
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Mock
	private PedidoService pedidoService;
	
	
	@Test
	void deberiaLanzarStockInsuficienteException() {
		
		// Arrange
		Usuario usuario = new Usuario();
		usuario.setUsername("cliente1");
		
		Producto producto = new Producto();
		producto.setId(1L);
		producto.setNombre("Laptop");
		producto.setStock(2);
		
		ItemPedidoRequest item = new ItemPedidoRequest();
		item.setProductoId(1L);
		item.setCantidad(5);
		
		CrearPedidoRequest request = new CrearPedidoRequest();
		request.setItems(List.of(item));
		
		when(usuarioRepository.findByUsername("cliente1"))
			.thenReturn(Optional.of(usuario));
		
		when(productoRepository.findById(1L))
			.thenReturn(Optional.of(producto));
		
		// Act + Assert
		assertThrows(
				StockInsuficienteException.class,
				() -> pedidoService.crearPedido(request, "cliente1"));
		
		// verify
		verify(pedidoRepository, never()).save(any(Pedido.class));
	}
	
	
	
}
