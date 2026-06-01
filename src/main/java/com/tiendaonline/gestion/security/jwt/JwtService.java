package com.tiendaonline.gestion.security.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service	// Marca esta clase como un componente de servicio en Spring, lo que permite su inyección en otras partes de la aplicación
// Servicio para manejar la generación y validación de tokens JWT (JSON Web Tokens)
public class JwtService {

	// Clave secreta utilizada para firmar y verificar los tokens JWT. En un entorno de producción,
	// esta clave debe ser almacenada de forma segura y no debe ser hardcodeada.
	// La clave debe estar codificada en Base64 y tener al menos 256 bits para HS256
	private static final String SECRET_KEY = "Y2xhdmVTdXBlclNlY3JldGFQYXJhSldUMTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkw"; 

	// Método para obtener la clave de firma a partir de la clave secreta
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date())	//
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))	// El token expirará en 1 hora
				.signWith(getSignKey(), SignatureAlgorithm.HS256)	// Firma el token utilizando la clave de firma y el algoritmo HS256
				.compact();
	}

	// Método para obtener la clave de firma a partir de la clave secreta
	public String extractUsername(String token) {
		return extractClaims(token).getSubject();

	}

	// Método para validar un token JWT, verificando su firma y asegurándose de que no haya expirado
	private Claims extractClaims(String token) {
		return Jwts.parserBuilder(		// Crea un parser para analizar el token JWT
				).setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)	// Analiza el token JWT y verifica su firma utilizando la clave de firma
				.getBody();
	}

	// Método para obtener la clave de firma a partir de la clave secreta
	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

		return Keys.hmacShaKeyFor(keyBytes);
	}

	// Método para validar un token JWT, verificando su firma y asegurándose de que no haya expirado
	public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);

        return username.equals(userDetails.getUsername());

	}
}
