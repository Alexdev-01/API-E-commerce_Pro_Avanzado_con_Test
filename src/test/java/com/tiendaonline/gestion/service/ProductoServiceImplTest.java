package com.tiendaonline.gestion.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tiendaonline.gestion.dto.producto.ProductoRequest;
import com.tiendaonline.gestion.exception.ResourceNotFoundException;
import com.tiendaonline.gestion.model.Categoria;
import com.tiendaonline.gestion.model.Producto;
import com.tiendaonline.gestion.repository.CategoriaRepository;
import com.tiendaonline.gestion.repository.ProductoRepository;
import com.tiendaonline.gestion.service.impl.ProductoServiceImpl;

@ExtendWith(MockitoExtension.class) // Anotación de JUnit 5 para habilitar la extensión de Mockito en esta clase de prueba
public class ProductoServiceImplTest {

	@Mock // Anotación de Mockito para crear un mock del repositorio de productos. Crea un repositorio falso.
	private ProductoRepository productoRepository;

	@Mock
	private CategoriaRepository categoriaRepository;

	@InjectMocks // Anotación de Mockito para inyectar los mocks en la clase bajo prueba. Inyecta ese repositorio falso en el service.
	private ProductoServiceImpl productoService;

	@Test
	void deberiaCrearProductoCorrectamente() {

		// Arrange. Configura los datos de prueba y el comportamiento esperado de los mocks
		Categoria categoria = new Categoria();
		categoria.setId(1L);

		ProductoRequest request = new ProductoRequest();
		request.setNombre("Laptop");
		request.setDescripcion("Laptop Gaming");
		request.setPrecio(new BigDecimal("1200"));
		request.setStock(10);
		request.setCategoriaId(1L);

		when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

		when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));

		// Act. Llama al método que se está probando
		Producto resultado = productoService.crearProducto(request);

		// Assert. Verifica que el resultado no sea nulo y que los valores sean los esperados
		assertNotNull(resultado);
		assertEquals("Laptop", resultado.getNombre());
		assertEquals(10, resultado.getStock());

		// Verifi. Verifica que los métodos del mock hayan sido llamados con los argumentos correctos
		verify(categoriaRepository).findById(1L);
		verify(productoRepository).save(any(Producto.class));
	}
	
	
	
	@Test
	void deberiaLanzarExcepcionCuandoCategoriaNoExiste() {

		// Arrange
		ProductoRequest request = new ProductoRequest();
		request.setNombre("Laptop");
		request.setDescripcion("Laptop Gaming");
		request.setPrecio(new BigDecimal("1200"));
		request.setStock(10);
		request.setCategoriaId(99L);
		
		when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());
		
		// Act & Assert
		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
				() -> productoService.crearProducto(request));
		
		assertEquals("Categoría no encontrada con id: 99",exception.getMessage());
		
		verify(categoriaRepository).findById(99L);
		verify(productoRepository, never()).save(any(Producto.class));	//nunca guardará un producto si la categoría no existe
	}
	
	
	
	
	

}
