package com.tiendaonline.gestion.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tiendaonline.gestion.model.Categoria;
import com.tiendaonline.gestion.repository.CategoriaRepository;
import com.tiendaonline.gestion.service.CategoriaService;
import com.tiendaonline.gestion.exception.ResourceNotFoundException;


@Service	// Marca esta clase como un componente de servicio en Spring
// Implementa la interfaz CategoriaService para proporcionar la lógica de negocio relacionada con las categorías
public class CategoriaServiceImpl implements CategoriaService {

	private final CategoriaRepository categoriaRepository;

	public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public Categoria crearCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	@Override
	public Categoria obtenerPorId(Long id) {
		return categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
	}
	

	@Override
	public List<Categoria> listarTodas() {
		return categoriaRepository.findAll();
	}
	

	@Override
	public Categoria actualizarCategoria(Long id, Categoria categoria) {
		
		Categoria existente = obtenerPorId(id);
		
		existente.setNombre(categoria.getNombre());
		existente.setDescripcion(categoria.getDescripcion());
		
		return categoriaRepository.save(existente);
	}

	@Override
	public void eliminarCategoria(Long id) {
		categoriaRepository.deleteById(id);
	}
	
}
