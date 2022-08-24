package com.nimvb.app.cryptoprice.security;

import com.nimvb.app.cryptoprice.dto.AuthorizationRequestContext;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AuthorizationService {

    OAuth2AccessTokenResponse authorize(AuthorizationRequestContext context);
}
