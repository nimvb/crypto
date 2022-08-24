package com.nimvb.app.cryptoprice.security;

import com.nimvb.app.cryptoprice.dto.AuthenticationRequestContext;
import com.nimvb.app.cryptoprice.model.User;

import java.util.Optional;

public interface AuthenticationService {

    Optional<User> authenticate(AuthenticationRequestContext context);
}
