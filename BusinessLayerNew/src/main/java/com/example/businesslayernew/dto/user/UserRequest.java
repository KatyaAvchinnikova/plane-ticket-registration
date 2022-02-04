package com.example.businesslayernew.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "Field user name cannot be empty")
    private String userName;

    @NotBlank(message = "Field first name cannot be empty")
    private String firstName;

    @NotBlank(message = "Field last name cannot be empty")
    private String lastName;

    @Past(message = "Date of birth cannot be in future or now")
    private Date dateOfBirth;

    @NotBlank(message = "Field email cannot be   empty")
    @Email
    private String email;

    @NotBlank(message = "Field password cannot be empty")
    @Size(min = 8)
    private String password;

}
