package com.tiendaonline.gestion.dto.auth;

import jakarta.validation.constraints.NotBlank;

// Solicitud de inicio de sesión (login) en la aplicación.
public class LoginRequest {
	
	@NotBlank  // Asegura que el campo no esté vacío o solo contenga espacios en blanco
	private String username;
	
	@NotBlank
	private String password;

	public LoginRequest() {
		super();
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
