package com.nimvb.app.cryptoprice.service;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public interface LogoutService {

    void logout(JwtAuthenticationToken token);
}
