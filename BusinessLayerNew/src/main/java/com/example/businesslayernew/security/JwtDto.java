package com.example.businesslayernew.security;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
public class JwtDto {

    private final String accessToken;

    private final String refreshToken;

}
