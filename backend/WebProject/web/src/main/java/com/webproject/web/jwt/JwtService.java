package com.webproject.web.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

    public static final String SECRECT_KEY = "Ww/bZYSEoR7xk5Ql/+cg6rD4pY3BmMLhEsza5s/x7VA=";

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claimsMap = new HashMap<>();
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        claimsMap.put("role", role);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .addClaims(claimsMap)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public Object getClaimsByKey(String token, String key) {
        Claims claims = getClaims(token);
        return claims.get(key);
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();
    }


    public <T> T exportToken(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = getClaims(token);
        return claimsTFunction.apply(claims);
    }

    public String getUserNameByToken(String token) {
        return exportToken(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date expiredDate = exportToken(token, Claims::getExpiration);
        return expiredDate.before(new Date());
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRECT_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }




}
