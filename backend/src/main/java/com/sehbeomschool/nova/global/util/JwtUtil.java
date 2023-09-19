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

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;

    public String createRefreshToken(Long userNo) {
        String refreshToken = create(String.valueOf(userNo), expireMin * 1000 * 60);
        redisUtil.set(String.valueOf(userNo), refreshToken, expireMin * 5);
        return refreshToken;
    }

    public String createJwtToken(String subject) {
        return create(String.valueOf(subject), expireMin * 1000 * 60);
    }

    public void logout(Long userNo, String accessToken) {
        accessToken = accessToken.substring(7);
        if (isValidToken(accessToken)) {
            redisUtil.delete(String.valueOf(userNo));
            redisUtil.setExcludeList(accessToken, "accessToken");
        }
    }

    private boolean isValidToken(String token) {
        String key = getSubject(token);

        return redisUtil.hasKey(key) && redisUtil.get(key).equals(token);
    }

    public String reCreateJwtToken(String refreshToken) {
        String key = getSubject(refreshToken);

        if (isValidToken(refreshToken)) {
            return createJwtToken(key);
        }
        return null;
    }

    public String create(String subject, int expTime) {
        if (expTime <= 0) {
            throw new RuntimeException("만료시간은 0이상");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
            .setSubject(subject)
            .signWith(signingKey, signatureAlgorithm)
            .setExpiration(new Date(System.currentTimeMillis() + expTime))
            .compact();
    }

    public String getSubject(String token) {
        token = token.substring(7);
        if (redisUtil.hasKeyExcludeList(token)) {
            throw new RuntimeException("이미 로그아웃하였습니다");
        }
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
            .build()
            .parseClaimsJws(token)
            .getBody();
        return claims.getSubject();
    }
}

