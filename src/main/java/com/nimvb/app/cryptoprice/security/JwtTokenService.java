package com.nimvb.app.cryptoprice.security;

import io.jsonwebtoken.Claims;
import lombok.NonNull;

import java.util.Map;

public interface JwtTokenService {

    String create(String subject,Long expiredIn, Map<String,Object> claims);

    Claims parse(String jwtToken);
}
