package com.tiendaonline.gestion.dto.auth;

import jakarta.validation.constraints.NotBlank;

public class AuthRequest {
	
	@NotBlank(message = "El username no es obligatorio")
	private String username;
	
	@NotBlank(message = "La contraseña no es obligatoria")
	private String password;

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
