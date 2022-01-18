package com.example.businesslayernew.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserRequest {

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String email;

    private String password;

}
