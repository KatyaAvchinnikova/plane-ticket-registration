package com.innowise.businesslayer.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {
    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
}
