package com.tiendaonline.gestion.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice	// Indica que esta clase maneja excepciones de manera global para los controladores REST
// Clase para manejar excepciones de manera global en la aplicación, proporcionando respuestas de error consistentes y detalladas para diferentes tipos de excepciones
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)	// Indica que este método maneja excepciones del tipo ResourceNotFoundException
	// Este método captura las excepciones de tipo ResourceNotFoundException, crea una respuesta de error con detalles y devuelve una respuesta con el estado HTTP 404 (Not Found)
	public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.name(),
				ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	// Maneja cualquier otra excepción genérica que no haya sido manejada por otros métodos específicos
	@ExceptionHandler(Exception.class)	// Indica que este método maneja cualquier excepción genérica que no haya sido manejada por otros métodos específicos
	// Este método captura cualquier excepción genérica, crea una respuesta de error con detalles y devuelve una respuesta con el estado HTTP 500 (Internal Server Error)
	public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.name(),
				"Error interno el servidor: ");
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
				
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)	// Indica que este método maneja excepciones del tipo MethodArgumentNotValidException, que se lanzan cuando la validación de los argumentos de un método falla
	// Este método captura las excepciones de validación, extrae los errores de validación y devuelve una respuesta con un mapa de campos y mensajes de error
	public ResponseEntity<Map<String, String>> handleValiadationErros(MethodArgumentNotValidException ex) {
		
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});
		
		return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler(BadRequestException.class)	// Indica que este método maneja excepciones del tipo BadRequestException, que se lanzan cuando hay un error de solicitud por parte del cliente
	// Este método captura las excepciones de tipo BadRequestException, crea una respuesta de error con detalles y devuelve una respuesta con el estado HTTP 400 (Bad Request)
	public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex, HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), ex.getMessage());
		
		return ResponseEntity.badRequest().body(error);
	}
	

}