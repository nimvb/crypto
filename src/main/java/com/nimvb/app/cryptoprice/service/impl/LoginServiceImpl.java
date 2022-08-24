package com.nimvb.app.cryptoprice.service.impl;

import com.nimvb.app.cryptoprice.dto.AuthenticationRequestContext;
import com.nimvb.app.cryptoprice.dto.AuthorizationRequestContext;
import com.nimvb.app.cryptoprice.model.Token;
import com.nimvb.app.cryptoprice.model.User;
import com.nimvb.app.cryptoprice.repository.TokenRepository;
import com.nimvb.app.cryptoprice.security.AuthenticationService;
import com.nimvb.app.cryptoprice.security.AuthorizationService;
import com.nimvb.app.cryptoprice.security.JwtTokenService;
import com.nimvb.app.cryptoprice.service.LoginService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final AuthenticationService authenticationService;
    private final AuthorizationService authorizationService;
    private final JwtTokenService      jwtTokenService;
    private final TokenRepository      tokenRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public OAuth2AccessTokenResponse login(String username, String password) {
        Optional<User> user = authenticationService.authenticate(AuthenticationRequestContext.builder()
                .username(username)
                .passwordMatcher(encodedPassword -> passwordEncoder.matches(password,encodedPassword))
                .build());
        if(user.isPresent()){
            Optional<Token> token = tokenRepository.findByUser_Id(user.get().getId());
            if(token.isPresent()){
                try {
                    Claims claims     = jwtTokenService.parse(token.get().getValue());
                    Date   expiration = claims.getExpiration();
                    if (Date.from(Instant.now()).before(expiration)) {
                        long diff = expiration.getTime() - Date.from(Instant.now()).getTime();
                        return OAuth2AccessTokenResponse
                                .withToken(token.get().getValue())
                                .expiresIn(TimeUnit.MILLISECONDS.toSeconds(diff))
                                .tokenType(OAuth2AccessToken.TokenType.BEARER)
                                .build();
                    }
                }catch (Exception ex){
                    tokenRepository.deleteById(token.get().getValue());
                }
            }
        }
        return user.map(value -> authorizationService
                .authorize(AuthorizationRequestContext
                        .builder()
                        .subject(value.getUsername())
                        .claims(new HashMap<>())
                        .build()))
                .orElseThrow();
    }
}
