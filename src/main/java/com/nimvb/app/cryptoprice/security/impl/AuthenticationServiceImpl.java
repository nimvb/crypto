package com.nimvb.app.cryptoprice.security.impl;

import com.nimvb.app.cryptoprice.dto.AuthenticationRequestContext;
import com.nimvb.app.cryptoprice.model.User;
import com.nimvb.app.cryptoprice.repository.UserRepository;
import com.nimvb.app.cryptoprice.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> authenticate(AuthenticationRequestContext context) {
        User user = new User();
        user.setUsername(context.getUsername());
        Optional<User> result = userRepository.findOne(Example.of(user,
                ExampleMatcher
                        .matching()
                        .withIgnorePaths("id", "password")));
        if(result.isPresent()){
            if(context.getPasswordMatcher().test(result.get().getPassword())){
                return result;
            }
        }
        return Optional.empty();
    }
}
