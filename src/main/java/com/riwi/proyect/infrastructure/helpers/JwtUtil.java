package com.riwi.proyect.infrastructure.helpers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    private Key getSignInKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    //Hacer el generador de token cuando haya entidad de user.
    public String generateToken(){
        return Jwts.builder()
                .compact();
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Boolean validateToken(String token, String username){
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    public Boolean isTokenExpired (String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        return claimsResolver.apply(extractAllClaim(token));
    }

    public Claims extractAllClaim(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
