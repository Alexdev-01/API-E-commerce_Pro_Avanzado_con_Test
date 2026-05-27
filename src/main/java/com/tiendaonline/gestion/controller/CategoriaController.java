package com.tiendaonline.gestion.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendaonline.gestion.model.Categoria;
import com.tiendaonline.gestion.service.CategoriaService;

@RestController  // Marca esta clase como un controlador REST en Spring
@RequestMapping("/categorias")  // Define la ruta base para todas las operaciones relacionadas con categorías (ej. /categorias, /categorias/{id}, etc.)
public class CategoriaController {

	private final CategoriaService categoriaService;

	public CategoriaController(CategoriaService categoriaService) {
		super();
		this.categoriaService = categoriaService;
	}
	
	@PostMapping	// Define que este método manejará solicitudes HTTP POST para crear una nueva categoría
	public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {		// @RequestBody indica que el cuerpo de la solicitud HTTP se deserializará en un objeto Categoria
		return ResponseEntity.ok(categoriaService.crearCategoria(categoria));
	}
	
	@GetMapping  // Define que este método manejará solicitudes HTTP GET para listar todas las categorías
	public ResponseEntity<List<Categoria>> listaCategorias() {
		return ResponseEntity.ok(categoriaService.listarTodas());
	}
	
	@GetMapping("/{id}")	// Define que este método manejará solicitudes HTTP GET para obtener una categoría específica por su ID (ej. /categorias/1)
	public ResponseEntity<Categoria> obtenerCategoria(@PathVariable Long id) {	// @PathVariable indica que el valor del ID se extraerá de la ruta de la solicitud HTTP
		return ResponseEntity.ok(categoriaService.obtenerPorId(id));
	}
	
	@PutMapping("/{id}") // Define que este método manejará solicitudes HTTP PUT para actualizar una categoría existente por su ID (ej. /categorias/1)
	public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
		return ResponseEntity.ok(categoriaService.actualizarCategoria(id, categoria));
	}
	
	@DeleteMapping("/{id}")  // Define que este método manejará solicitudes HTTP DELETE para eliminar una categoría por su ID (ej. /categorias/1)
	public ResponseEntity<Void> eliminarCategoria (@PathVariable Long id) {
		categoriaService.eliminarCategoria(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
