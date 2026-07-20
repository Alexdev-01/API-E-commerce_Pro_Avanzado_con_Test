package com.tiendaonline.gestion.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tiendaonline.gestion.model.Categoria;
import com.tiendaonline.gestion.model.Producto;
import com.tiendaonline.gestion.repository.CategoriaRepository;
import com.tiendaonline.gestion.repository.ProductoRepository;
import com.tiendaonline.gestion.service.ProductoService;
import org.springframework.stereotype.Service;

import com.tiendaonline.gestion.dto.producto.ProductoRequest;
import com.tiendaonline.gestion.dto.producto.ProductoResponse;
import com.tiendaonline.gestion.exception.ResourceNotFoundException;



@Service	// Marca esta clase como un componente de servicio en Spring
// Implementa la interfaz ProductoService para proporcionar la lógica de negocio relacionada con los productos
public class ProductoServiceImpl implements ProductoService{

	private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);

	private final ProductoRepository productoRepository;
	private final CategoriaRepository categoriaRepository;

	public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
		this.productoRepository = productoRepository;
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public Producto crearProducto(ProductoRequest request) {
		Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
				.orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + request.getCategoriaId()));

		Producto producto = new Producto();
		producto.setNombre(request.getNombre());
		producto.setDescripcion(request.getDescripcion());
		producto.setPrecio(request.getPrecio());
		producto.setStock(request.getStock());
		producto.setCategoria(categoria);
		
		log.info("Creando producto correctamente: {}", request.getNombre());

		return productoRepository.save(producto);
	}

	// Método auxiliar interno para obtener la entidad Producto (no el DTO)
	private Producto obtenerEntidadPorId(Long id) {
		return productoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
	}

	@Override
	public Producto actualizarProducto(Long id, Producto producto) {
		Producto existente = obtenerEntidadPorId(id);

		existente.setNombre(producto.getNombre());
		existente.setDescripcion(producto.getDescripcion());
		existente.setPrecio(producto.getPrecio());
		existente.setStock(producto.getStock());
		existente.setCategoria(producto.getCategoria());

		log.info("Actualizado producto correctamente: {}", existente.getNombre());
		
		return productoRepository.save(existente);
	}

	@Override
	public void eliminarProducto(Long id) {
		Producto producto = obtenerEntidadPorId(id);
		producto.setActivo(false); 
		productoRepository.save(producto);
		
		log.info("Eliminado producto correctamente: {}", producto.getNombre());

	}

	@Override
	public ProductoResponse obtenerPorId(Long id) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
		
		log.info("Obtener producto correctamente: {}", producto.getNombre());

		return mapToResponse(producto);
	}

	@Override
	public List<ProductoResponse> listarTodos() {
		return productoRepository.findAll()
				.stream().filter(Producto::getActivo).map(this::mapToResponse).toList();
	}
	
	// Método privado para mapear un objeto Producto a un ProductoResponse
	private ProductoResponse mapToResponse(Producto producto) {
		return new ProductoResponse(
				producto.getId(), producto.getNombre(), producto.getDescripcion(),producto.getPrecio(),
				producto.getStock(), producto.getCategoria() != null? producto.getCategoria().getNombre() : null);
	}

	@Override
	public Page<ProductoResponse> listarProductosPaginados(int page, int size) {
		
		//todos los productos
		//Page<Producto> producto = productoRepository.findAll(PageRequest.of(page, size));
		
		//solo los productos activos
		Page<Producto> producto = productoRepository.findByActivoTrue(PageRequest.of(page, size));
		
		return producto.map(this::mapToResponse);
	}

	@Override
	public Page<ProductoResponse> filtrarPrductos(Long categoriaId, BigDecimal precioMin, BigDecimal precioMax, int page, int size) {
		Page<Producto> producto = productoRepository.filtrarProductos(categoriaId, precioMin, precioMax, PageRequest.of(page, size));
		return producto.map(this::mapToResponse);
	}



}
