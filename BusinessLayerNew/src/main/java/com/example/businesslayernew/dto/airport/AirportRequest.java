package com.example.businesslayernew.dto.airport;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AirportRequest {
    @NotBlank
    private String name;
}
