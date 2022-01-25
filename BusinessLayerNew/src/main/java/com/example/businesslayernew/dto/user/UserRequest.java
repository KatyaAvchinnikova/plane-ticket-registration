package com.example.businesslayernew.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRequest {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private Date dateOfBirth;

    @Email
    private String email;

    @Size(min = 8)
    private String password;

}
