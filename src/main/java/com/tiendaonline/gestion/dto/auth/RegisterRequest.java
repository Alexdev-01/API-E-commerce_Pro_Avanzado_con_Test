package com.tiendaonline.gestion.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Solicitud de registro (registro) nuevo usuario en la aplicación.
public class RegisterRequest {
	
	@NotBlank(message = "El username no es obligatorio")
	private String username;
	
	@Email(message = "Email invalido")
	@NotBlank(message = "El email no es obligatorio")
	private String email;
	
	@NotBlank(message = "La contraseña no es obligatoria")
	@Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String password;

	
	public RegisterRequest() {
		super();
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
