package com.expedienteclinico.expedienteclinico.controllers;

import com.expedienteclinico.expedienteclinico.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/test")
@RequiredArgsConstructor
public class AuthTestController {

    private final JwtService jwtService;

    /**
     * 1. Generar Token: Simula un login exitoso.
     */
    @GetMapping("/generate")
    public ResponseEntity<Map<String, Object>> generateMockToken(@RequestParam String username) {
        // Simulamos inyectar un rol en los claims
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", "ROLE_MEDICO");

        String token = jwtService.generateToken(extraClaims, username);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Token generado exitosamente");
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    /**
     * 2. Validar Token: Verifica si el token no ha sido alterado y extrae su contenido.
     */
    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyMockToken(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam String expectedUsername) {

        Map<String, Object> response = new HashMap<>();

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.put("message", "Falta el header Authorization o no usa el esquema Bearer");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        String token = authHeader.substring(7); // Extrae la cadena después de "Bearer "

        try {
            boolean isValid = jwtService.isTokenValid(token, expectedUsername);
            String extractedUser = jwtService.extractUsername(token);

            response.put("message", isValid ? "Token Válido" : "Token Inválido (No coincide el usuario)");
            response.put("extractedSubject", extractedUser);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Captura excepciones críticas de JJWT: Firma alterada, expirado, malformado.
            response.put("message", "Fallo de validación criptográfica");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}