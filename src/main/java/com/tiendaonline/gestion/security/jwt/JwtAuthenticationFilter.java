package com.tiendaonline.gestion.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tiendaonline.gestion.security.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component	// Marca esta clase como un componente de Spring, lo que permite su detección automática y su uso en la configuración de seguridad
// Filtro de autenticación JWT que se ejecuta una vez por cada solicitud HTTP. Este filtro intercepta las solicitudes entrantes, extrae el token JWT del encabezado de autorización, valida el token y establece la autenticación en el contexto de seguridad si el token es válido.
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	
	public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
		super();
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}

	// Excluir las rutas de autenticación del filtro JWT
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String path = request.getServletPath();
		return path.startsWith("/auth/");
	}

	// Método que se ejecuta para cada solicitud HTTP. Intercepta la solicitud, extrae el token JWT del encabezado de autorización, valida el token y establece la autenticación en el contexto de seguridad si el token es válido.
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		
		String token = authHeader.substring(7);	// Extrae el token JWT del encabezado de autorización, eliminando el prefijo "Bearer " son los 7 primeros caracteres
		String username = jwtService.extractUsername(token);  // Extrae el nombre de usuario del token JWT utilizando el servicio JwtService
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);	
			
			if (jwtService.isTokenValid(token, userDetails)) {
				
				// Si el token es válido, crea un objeto UsernamePasswordAuthenticationToken con los detalles del usuario y sus autoridades, y lo establece en el contexto de seguridad de Spring para que la autenticación esté disponible durante el procesamiento de la solicitud.
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(authToken);	// Establece la autenticación en el contexto de seguridad de Spring
			}
		}
		
		filterChain.doFilter(request, response);	// Continúa con la cadena de filtros para procesar la solicitud
	}
	
	

	
}
