package com.example.businesslayernew.dto.airport;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//TODO: рекомендую убрать пакет response и респонсы класть в пакет dto с названием entityNameDto
public class AirportResponse {

    private Long id;

    private String name;

}
