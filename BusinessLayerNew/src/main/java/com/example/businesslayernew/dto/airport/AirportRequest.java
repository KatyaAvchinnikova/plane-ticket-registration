package com.example.businesslayernew.dto.airport;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
@Getter
public class AirportRequest {

    @NotBlank
    private String name;
}
