package com.tiendaonline.gestion.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tiendaonline.gestion.security.jwt.JwtAuthenticationFilter;

@Configuration	// Marca esta clase como una clase de configuración de Spring, lo que permite definir beans y configuraciones relacionadas con la seguridad
@EnableWebSecurity	// Habilita la configuración de seguridad web en la aplicación, lo que permite personalizar la seguridad de las solicitudes HTTP
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtFilter;

	public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
		this.jwtFilter = jwtFilter;
	}
	
	@Bean	// Marca este método como un bean de Spring, lo que permite su gestión y uso en otras partes de la aplicación
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/auth/**").permitAll()
				.requestMatchers("/productos/**").permitAll()
				.requestMatchers("/categorias/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean	// Marca este método como un bean de Spring, lo que permite su gestión y uso en otras partes de la aplicación
	// Define un bean de PasswordEncoder que utiliza BCrypt para cifrar las contraseñas, lo que mejora la seguridad al almacenar las contraseñas de los usuarios
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	// Define un bean de AuthenticationManager que se obtiene a través de la configuración de autenticación, lo que permite gestionar la autenticación de los usuarios en la aplicación
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration configuration) throws Exception {
		
		return configuration.getAuthenticationManager();
	}

}
