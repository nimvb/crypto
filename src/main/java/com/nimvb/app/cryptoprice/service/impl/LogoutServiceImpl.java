package com.nimvb.app.cryptoprice.service.impl;

import com.nimvb.app.cryptoprice.repository.TokenRepository;
import com.nimvb.app.cryptoprice.service.LogoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogoutServiceImpl implements LogoutService {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(JwtAuthenticationToken token) {
        tokenRepository.deleteById(token.getToken().getTokenValue());
    }
}
