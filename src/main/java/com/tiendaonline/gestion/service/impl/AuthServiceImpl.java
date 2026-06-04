package com.tiendaonline.gestion.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tiendaonline.gestion.dto.auth.AuthResponse;
import com.tiendaonline.gestion.dto.auth.LoginRequest;
import com.tiendaonline.gestion.dto.auth.RegisterRequest;
import com.tiendaonline.gestion.model.Rol;
import com.tiendaonline.gestion.model.Usuario;
import com.tiendaonline.gestion.repository.UsuarioRepository;
import com.tiendaonline.gestion.security.jwt.JwtService;
import com.tiendaonline.gestion.service.AuthService;

@Service	// Marca esta clase como un servicio de Spring, lo que permite su gestión y uso en otras partes de la aplicación
public class AuthServiceImpl implements AuthService {
	
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	

	public AuthServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
			AuthenticationManager authenticationManager) {
		super();
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}
	
	

	@Override
	public AuthResponse register(RegisterRequest request) {
		
		Usuario usuario = new Usuario();
		
		usuario.setUsername(request.getUsername());
		usuario.setEmail(request.getEmail());
		usuario.setPassword(passwordEncoder.encode(request.getPassword()));
		usuario.setRole(Rol.CLIENTE);
		
		usuarioRepository.save(usuario);
		
		String token = jwtService.generateToken(org.springframework.security.core.userdetails.User
				.builder()
				.username(usuario.getUsername())
				.password(usuario.getPassword())
				.roles(usuario.getRole().name())
				.build()
		);
		
		return new AuthResponse(token);
	}

	@Override
	public AuthResponse login(LoginRequest request) {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		Usuario usuario = usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
		
		String token = jwtService.generateToken(org.springframework.security.core.userdetails.User
				.builder()
				.username(usuario.getUsername())
				.password(usuario.getPassword())
				.roles(usuario.getRole().name())
				.build()
		);
		
		return new AuthResponse(token);
		
	}

}
