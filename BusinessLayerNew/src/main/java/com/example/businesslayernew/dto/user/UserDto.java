package com.example.businesslayernew.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto {

    private String userName;

    private String firstName;

    private String lastName;
//TODO: DATE?
    private Date dateOfBirth;

    private String email;

}
