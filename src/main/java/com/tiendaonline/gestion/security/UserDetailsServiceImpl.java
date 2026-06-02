package com.tiendaonline.gestion.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tiendaonline.gestion.model.Usuario;
import com.tiendaonline.gestion.repository.UsuarioRepository;

@Service // Marca esta clase como un componente de servicio en Spring, lo que permite su inyección en otras partes de la aplicación
// Implementación de la interfaz UserDetailsService, que es utilizada por Spring Security para cargar los detalles del usuario durante el proceso de autenticación
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	// Método que carga los detalles del usuario a partir de su nombre de usuario. Si el usuario no se encuentra, lanza una excepción UsernameNotFoundException.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

		return org.springframework.security.core.userdetails.User
				.builder()
				.username(usuario.getUsername())
				.password(usuario.getPassword())
				.roles(usuario.getRole().name())
				.build();
	}

}
