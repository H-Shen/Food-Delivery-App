package com.mealwrap.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

@Component
public class JwtUtils {

    public final static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public final static String SECRET_KEY = "mexcKtZSTfks2aNSNzq3XQ014VhC7rnfG36bTdRLls4=";

    // 通过User ID生成token
    public static String getToken(Integer id) {
        if (id == null) {
            return null;
        }
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key    signingkey        = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());
        // 创建并返回token
        return Jwts.builder()
                .setSubject(String.valueOf(id))
                .claim("id", id)
                .signWith(SIGNATURE_ALGORITHM, signingkey)
                .compact();
    }

    // 通过token返回User ID
    public static Integer getUserId(String token) {
        if (token == null) {
            return null;
        }
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key    signingkey        = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());
        Claims claims = Jwts.parser()
                .setSigningKey(signingkey)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("id", Integer.class);
    }

}
