package com.example.businesslayernew.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
//    TODO: зачем поле? Role.USER.name() недостаточно?
    //    TODO: зачем поле? Role.USER.name() недостаточно?(х2)
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");
    private final String role;
}
