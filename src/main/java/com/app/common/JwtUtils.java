package com.app.common;

import com.app.entity.User;
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

    /**
     * generate token by the User ID
     **/
    public static String getToken(User user) {
        if (user == null) {
            return null;
        }
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key    signingkey        = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .claim("phone", user.getPhone())
                .claim("address", user.getAddress())
                .signWith(SIGNATURE_ALGORITHM, signingkey)
                .compact();
    }

    /** fetch the user ID from the token **/
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
