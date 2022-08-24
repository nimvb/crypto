package com.nimvb.app.cryptoprice.service.impl;

import com.nimvb.app.cryptoprice.model.User;
import com.nimvb.app.cryptoprice.repository.UserRepository;
import com.nimvb.app.cryptoprice.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    @Override
    public void register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
    }
}
