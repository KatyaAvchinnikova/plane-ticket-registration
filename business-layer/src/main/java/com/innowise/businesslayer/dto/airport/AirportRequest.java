package com.innowise.businesslayer.dto.airport;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AirportRequest {
    @NotBlank
    private String name;
}
