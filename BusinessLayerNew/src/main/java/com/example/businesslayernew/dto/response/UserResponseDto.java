package com.example.businesslayernew.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserResponseDto {

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String email;

}
