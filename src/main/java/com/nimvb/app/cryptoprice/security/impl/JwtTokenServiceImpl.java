package com.nimvb.app.cryptoprice.security.impl;

import com.nimvb.app.cryptoprice.security.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value(value = "${jwt.secret:#{T(java.util.Base64).encoder.encodeToString(T(com.nimvb.app.cryptoprice.security.KeyGenerator).generate().encoded)}}")
    private String secret;

    @Override
    public String create(String subject, Long expiredIn, Map<String, Object> claims) {
        Date issuedAt  = new Date(System.currentTimeMillis());
        Date   expiredAt = Date.from(issuedAt.toInstant().plusSeconds(expiredIn));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    @Override
    public Claims parse(String jwtToken) {
        Assert.hasText(jwtToken,"JWT token is required");
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwtToken)
                .getBody();
    }
}
