package com.tiendaonline.gestion.service;

import java.util.List;

import com.tiendaonline.gestion.model.Categoria;

public interface CategoriaService {
	
	Categoria crearCategoria(Categoria categoria);
	
	Categoria obtenerPorId(Long id);
	
    List<Categoria> listarTodas();
    
    Categoria actualizarCategoria(Long id, Categoria categoria);
    
    void eliminarCategoria(Long id);

}
