package com.innowise.businesslayer.security;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtDto {
    private final String accessToken;
    private final String refreshToken;
}
