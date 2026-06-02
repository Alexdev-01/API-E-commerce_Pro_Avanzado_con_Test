package com.tiendaonline.gestion.service;

import com.tiendaonline.gestion.dto.auth.AuthRequest;
import com.tiendaonline.gestion.dto.auth.AuthResponse;
import com.tiendaonline.gestion.dto.auth.LoginRequest;
import com.tiendaonline.gestion.dto.auth.RegisterRequest;

import jakarta.validation.Valid;

public interface AuthService {

	AuthResponse register(RegisterRequest request);

	AuthResponse login(@Valid AuthRequest request);
	
}
