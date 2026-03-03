package com.expedienteclinico.expedienteclinico.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // 1. Desactivar CSRF: Obligatorio para APIs REST sin estado (Stateless) que usan JWT.
                // Si no se desactiva, Spring bloqueará todos los métodos POST, PUT y DELETE.
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Configuración CORS: Evita bloqueos del navegador cuando el frontend (React/Angular) consuma la API.
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 3. Gestión de Sesiones: Se instruye a Spring para que NO cree sesiones en el servidor (JSESSIONID).
                // Cada petición deberá ser autorizada independientemente mediante su token JWT.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 4. Reglas de Autorización de Rutas (RBAC perimetral)
                .authorizeHttpRequests(auth -> auth
                        // Se permite el acceso público (sin token) a las rutas de autenticación y pruebas
                        .requestMatchers("/api/auth/**").permitAll()
                        // Cualquier otra ruta en el sistema exigirá estar autenticado
                        .anyRequest().authenticated()
                );

        // Nota: En la siguiente fase inyectaremos aquí el filtro personalizado que leerá el JWT.

        return http.build();
    }

    /**
     * Definición estricta de orígenes permitidos (Whitelisting).
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // En producción, reemplazar "*" por los dominios reales del frontend (Ej. "https://hospital.com")
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}