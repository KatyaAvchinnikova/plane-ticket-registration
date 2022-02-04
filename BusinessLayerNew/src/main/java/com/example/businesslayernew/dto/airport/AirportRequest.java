package com.example.businesslayernew.dto.airport;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
//TODO: зачем сеттер? не очищены лишние импорты
@Getter
@Setter
public class AirportRequest {
    @NotBlank
    private String name;
}
