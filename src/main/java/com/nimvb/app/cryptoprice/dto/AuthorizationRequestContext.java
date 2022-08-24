package com.nimvb.app.cryptoprice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Builder
@Getter
@RequiredArgsConstructor
public class AuthorizationRequestContext {
    private final String             subject;
    private final Map<String,Object> claims;
}
