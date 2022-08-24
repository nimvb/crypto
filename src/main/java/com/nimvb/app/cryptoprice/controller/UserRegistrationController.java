package com.nimvb.app.cryptoprice.controller;

import com.nimvb.app.cryptoprice.dto.UserDetailsContext;
import com.nimvb.app.cryptoprice.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRegistrationController {

    private final RegistrationService registrationService;
    @PostMapping
    public void register(@RequestBody UserDetailsContext context){
        registrationService.register(context.getUsername(),context.getPassword());
    }
}
