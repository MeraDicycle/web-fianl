package com.zjw.gmbackend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {

    // ⚠️ 项目内固定即可，不要外传
    private static final String SECRET_KEY = "green-music-zjw666=zjw6666-zjw66666";

    // token 有效期（7 天）
    private static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;

    private static byte[] keyBytes() {
        return SECRET_KEY.getBytes(StandardCharsets.UTF_8);
    }

    public static String generateToken(Long userId, String username) {
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(
                        Keys.hmacShaKeyFor(keyBytes()),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(keyBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static Long getUserId(String token) {
        return parseToken(token).get("userId", Long.class);
    }
}
