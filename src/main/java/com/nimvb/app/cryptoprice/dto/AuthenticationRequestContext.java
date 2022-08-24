package com.nimvb.app.cryptoprice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

@Builder
@Getter
@RequiredArgsConstructor
public class AuthenticationRequestContext {
    private final String username;
    private final Predicate<String> passwordMatcher;
}
