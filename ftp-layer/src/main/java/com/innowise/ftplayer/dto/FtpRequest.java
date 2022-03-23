package com.innowise.ftplayer.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FtpRequest {

    @NotBlank(message = "Path name shouldn't be empty")
    private String path;

}
