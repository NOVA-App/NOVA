package com.sehbeomschool.nova.global.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Autowired
    private RedisUtil redisUtil;

    @Value("${jwt.expmin}")
    private int expireMin;

    @Value("${jwt.refresh-expmin}")
    private int refreshExpireMin;

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;

    public String createRefreshToken(Long userId) {
        String refreshToken = create(userId, refreshExpireMin);
        redisUtil.set(String.valueOf(userId), refreshToken, refreshExpireMin);
        return refreshToken;
    }

    public String createJwtToken(Long userId) {
        return create(userId, expireMin);
    }

    public void logout(Long userNo, String accessToken) {
        accessToken = accessToken.substring(7);
        try {
            getUserId(accessToken);
            redisUtil.setExcludeList(accessToken, "accessToken");
        } catch (Exception e) {
            throw new RuntimeException("유효하지 않은 토큰");
        }
    }

    public String create(Long userId, int expTime) {
        if (expTime <= 0) {
            throw new RuntimeException("만료시간은 0이상");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        return Jwts.builder()
            .setClaims(claims)
            .signWith(signingKey, signatureAlgorithm)
            .setExpiration(new Date(System.currentTimeMillis() + expTime))
            .compact();
    }

    public Long getUserId(String token) {
        token = token.substring(7);
        if (redisUtil.hasKeyExcludeList(token)) {
            throw new RuntimeException("이미 로그아웃하였습니다");
        }
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
            .build()
            .parseClaimsJws(token)
            .getBody();
        return claims.get("userId", Long.class);
    }
}

