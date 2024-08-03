package com.anonfedora.config;

import java.security.SecureRandom;
import java.security.Key;
import java.util.Date;

import java.util.Base64;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

@Component
public class JwtTokenProvider {

    // private static String jwtSecret = generateSecretKey();
    private String jwtSecret = "base64encoded";
    private long jwtExpirationDate = 3600000;

    public String generateToken(Authentication authentication) {

        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder().subject(username).issuedAt(new Date()).expiration(expireDate)
                .signWith(key(), SignatureAlgorithm.HS256).compact();

        return token;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // Extract key from JWT
    public String getUsername(String token) {
        return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean validateToken(String token) {
        Jwts.parser().verifyWith((SecretKey) key()).build().parse(token);
        return true;
    }

    // public static Claims parseToken(String token){
    // return Jwts.parser().verifyWith(null)
    // }

    // private static SecretKey getSecretKey(){
    // byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
    // }

    @SuppressWarnings("deprecation")
    public String generateSecretKey() {
        int length = 32;

        SecureRandom secureRandom = new SecureRandom();

        byte[] keyBytes = new byte[length];

        secureRandom.nextBytes(keyBytes);

        return Base64.getEncoder().encodeToString(keyBytes);
    }
}
