package com.tiendaonline.gestion.dto.auth;

// Respuesta de autenticación que se devuelve después de un registro o login exitoso, generalmente contiene un token JWT.
public class AuthResponse {
	
	private String token;

	public AuthResponse() {
		super();
	}

	public AuthResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
