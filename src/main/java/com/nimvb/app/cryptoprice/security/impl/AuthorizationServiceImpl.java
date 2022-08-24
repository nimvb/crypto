package com.nimvb.app.cryptoprice.security.impl;

import com.nimvb.app.cryptoprice.dto.AuthorizationRequestContext;
import com.nimvb.app.cryptoprice.model.Token;
import com.nimvb.app.cryptoprice.model.User;
import com.nimvb.app.cryptoprice.repository.TokenRepository;
import com.nimvb.app.cryptoprice.repository.UserRepository;
import com.nimvb.app.cryptoprice.security.AuthorizationService;
import com.nimvb.app.cryptoprice.security.JwtTokenService;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
    private final JwtTokenService jwtTokenService;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    @Value(value = "${token.seconds_to_expires:36000}")
    private Long SECONDS_TO_EXPIRES;
    @Override
    public OAuth2AccessTokenResponse authorize(AuthorizationRequestContext context) {
        String token = jwtTokenService.create(context.getSubject(), SECONDS_TO_EXPIRES, context.getClaims());
        Token  entity = new Token();
        entity.setValue(token);
        User target = new User();
        target.setUsername(context.getSubject());
        User user  = userRepository.findOne(Example.of(target,
                ExampleMatcher.matching().withIgnorePaths("id", "password")))
                .orElseThrow();
        entity.setUser(user);
        tokenRepository.save(entity);
        return OAuth2AccessTokenResponse
                .withToken(token)
                .expiresIn(SECONDS_TO_EXPIRES)
                .tokenType(OAuth2AccessToken.TokenType.BEARER)
                .build();
    }
}
