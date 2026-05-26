package com.tiendaonline.gestion.controller;

import com.tiendaonline.gestion.dto.auth.AuthRequest;
import com.tiendaonline.gestion.dto.auth.AuthResponse;
import com.tiendaonline.gestion.dto.auth.LoginRequest;
import com.tiendaonline.gestion.dto.auth.RegisterRequest;
import com.tiendaonline.gestion.dto.common.ApiResponse;
import com.tiendaonline.gestion.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController		// Marca esta clase como un controlador REST, lo que permite manejar solicitudes HTTP y devolver respuestas en formato JSON
@RequestMapping("/auth")	// Define la ruta base para todas las solicitudes manejadas por este controlador, lo que significa que todas las rutas definidas en este controlador comenzarán con "/auth"
// Controlador para manejar las solicitudes de autenticación, como el registro y el inicio de sesión de los usuarios. Utiliza el servicio de autenticación para procesar las solicitudes y generar respuestas adecuadas.
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	
	@PostMapping("/register")	// Define una ruta para manejar las solicitudes de registro de usuarios, lo que significa que las solicitudes POST a "/auth/register"
	//	Recibe un objeto RegisterRequest en el cuerpo de la solicitud, lo que permite obtener los datos necesarios para registrar a un nuevo usuario. El método devuelve una respuesta con un objeto AuthResponse que contiene el token JWT generado para el usuario registrado.
	public ResponseEntity<ApiResponse<Object>> register(@Valid @RequestBody RegisterRequest request) {
		
	    authService.register(request);

		return ResponseEntity.ok(new ApiResponse<>(true, "Usuario registrado correctamente", null));
	}
	
	@PostMapping("/login")
	//	Recibe un objeto LoginRequest en el cuerpo de la solicitud, lo que permite obtener los datos necesarios para autenticar a un usuario. El método devuelve una respuesta con un objeto AuthResponse que contiene el token JWT generado para el usuario autenticado.
	public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody AuthRequest request) {
		
		AuthResponse response = authService.login(request);
		
		return ResponseEntity.ok(new ApiResponse<>(true,"Login exitoso",response));
	}
	
}
