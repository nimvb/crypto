package com.nimvb.app.cryptoprice.controller;


import com.nimvb.app.cryptoprice.dto.UserDetailsContext;
import com.nimvb.app.cryptoprice.service.LoginService;
import com.nimvb.app.cryptoprice.service.LogoutService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/logout")
@RequiredArgsConstructor
@SecurityRequirement(name = "BEARER_AUTHENTICATION")
public class LogoutController {

    private final LogoutService logoutService;
    @PostMapping
    public void login(JwtAuthenticationToken authenticationToken){
        logoutService.logout(authenticationToken);
    }
}
