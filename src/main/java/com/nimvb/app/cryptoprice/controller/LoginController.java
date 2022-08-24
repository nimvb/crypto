package com.nimvb.app.cryptoprice.controller;

import com.nimvb.app.cryptoprice.dto.UserDetailsContext;
import com.nimvb.app.cryptoprice.service.LoginService;
import com.nimvb.app.cryptoprice.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tokens")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    @PostMapping
    public OAuth2AccessTokenResponse login(@RequestBody UserDetailsContext context){
        return loginService.login(context.getUsername(),context.getPassword());
    }
}
