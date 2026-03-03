package com.expedienteclinico.expedienteclinico.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.HexFormat;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Clave estática de 256 bits en HEX (Fallback seguro para desarrollo).
    // En producción, DEBE ser inyectada desde el SO o un gestor de secretos.
    @Value("${JWT_SECRET_KEY:QE5jUmZValhuMnIyNXU4eC9BM0QoRytLYlBkU2dWb1k=}")
    private String secretKey;

    // Tiempo de vida del token: 24 horas en milisegundos
    @Value("${JWT_EXPIRATION_TIME:86400000}")
    private long jwtExpiration;

    /**
     * Genera un token JWT para un usuario específico.
     */
    public String generateToken(String username) {
        return generateToken(new HashMap<>(), username);
    }

    /**
     * Sobrecarga para inyectar Claims (cargas útiles) adicionales, como roles o permisos.
     */
    public String generateToken(Map<String, Object> extraClaims, String username) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(), Jwts.SIG.HS256)
                .compact();
    }

    /**
     * Extrae el nombre de usuario (Subject) del payload del token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Valida la integridad de la firma y que el token pertenezca al usuario esperado.
     */
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrae un Claim específico utilizando una función de mapeo.
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Parsea el token. Si la firma es inválida, está expirado o fue alterado,
     * lanzará excepciones criptográficas (SignatureException, ExpiredJwtException).
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Convierte la cadena secreta HEX/Base64 en un objeto SecretKey criptográficamente válido.
     */
    private SecretKey getSignInKey() {
        byte[] keyBytes = io.jsonwebtoken.io.Decoders.BASE64.decode(secretKey);
        return io.jsonwebtoken.security.Keys.hmacShaKeyFor(keyBytes);
    }
}