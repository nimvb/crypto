package com.nimvb.app.cryptoprice.service;

import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;

import javax.transaction.Transactional;

@Transactional
public interface LoginService {

    OAuth2AccessTokenResponse login(String username,String password);
}
