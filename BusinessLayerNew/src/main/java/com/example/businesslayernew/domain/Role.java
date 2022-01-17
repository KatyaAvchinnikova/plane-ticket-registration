package com.example.businesslayernew.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public enum Role {
//    TODO: зачем поле? Role.USER.name() недостаточно?
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");
    private final String role;
}
