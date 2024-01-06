package io.dev.authone.jwt;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

  @Value("${jwt.secret.key}")
  private String secretKey;
  @Value("${jwt.time.expire}")
  private String timeExpiration;

  public String generateAccessToken(String username) {
  return Jwts.builder()
    .subject(username)
    .issuedAt(new Date(System.currentTimeMillis()))
    .expiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
    .signWith(getSignatureKey(), Jwts.SIG.HS256)
    .compact();
  }

  public SecretKey getSignatureKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public boolean isTokenValid(String token) {
    try {
      Jwts.parser()
        .verifyWith(getSignatureKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
      return true;
    } catch(Exception e) {
      return false;
    }
  }

  public String getUsernameFromToken(String token) {
    return getClaim(token, Claims::getSubject);
  }

  public <T> T getClaim(String token, Function<Claims, T> claimsFunc) {
    Claims claims = extractAllClaims(token);
    return claimsFunc.apply(claims);
  }

  public Claims extractAllClaims(String token) {
    return Jwts.parser()
        .verifyWith(getSignatureKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

}
