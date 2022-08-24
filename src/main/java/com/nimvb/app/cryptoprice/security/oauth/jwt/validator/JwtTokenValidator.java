package com.nimvb.app.cryptoprice.security.oauth.jwt.validator;

import com.nimvb.app.cryptoprice.model.Token;
import com.nimvb.app.cryptoprice.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtTokenValidator implements OAuth2TokenValidator<Jwt> {
    private final TokenRepository repository;
    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        String value = token.getTokenValue();
        if(repository.existsById(value)){
            return OAuth2TokenValidatorResult.success();
        }
        return OAuth2TokenValidatorResult.failure(new OAuth2Error("invalid token"));
    }
}
