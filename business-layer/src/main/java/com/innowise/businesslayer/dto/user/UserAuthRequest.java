package com.innowise.businesslayer.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserAuthRequest {
    @NotBlank(message = "Field user name cannot be empty")
    private String userName;

    @NotBlank(message = "Field password cannot be empty")
    @Size(min = 8)
    private String password;
}
