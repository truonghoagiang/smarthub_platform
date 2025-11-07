package com.smarthub.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.refresh-token-expiration}")
    private Long refreshTokenExpiration;

    @Value("${jwt.access-token-expiration}")
    private Long accessTokenExpiration;


    private SecretKey getSignInKey(){
        byte[] signKey = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(signKey);
    }

    private Claims extractAllClaims(String token){
        try {
            return Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }catch (ExpiredJwtException e){
            throw new JwtException("Expired or invalid JWT token --" + e.getMessage());
        }
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        try{
            logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
            final Claims  claims  = extractAllClaims(token);
            return claimsResolver.apply(claims);
        }catch(JwtException e){
            logger.error(claimsResolver.toString() + " --null" + e.getMessage());
            return null;
        }
    }

    public String generateAccessToken(UserDetails userDetails) {
        return generateAccessToken(new HashMap<>(), userDetails);
    }

    public String generateAccessToken(Map<String, Object> extractClaims, UserDetails userDetails) {
        return tokenBuilder(extractClaims,userDetails,accessTokenExpiration);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return tokenBuilder(new HashMap<>(),userDetails,refreshTokenExpiration);
    }

    private String tokenBuilder(Map<String, Object> extractClaims,
                                UserDetails userDetails,
                                long expiration) {
        return Jwts
                .builder()
                .claims(extractClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSignInKey(),Jwts.SIG.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username .equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }

    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public Long getAccessTokenExpiration() {
        return accessTokenExpiration;
    }
}
