package com.example.businesslayernew.dto.airport;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//TODO: зачем Dto в названии?
public class AirportRequest {
//    TODO:здесь и далее для реквестов: реквест на создание и апдейт одинаковый? если нет - разнести с указанием в наименовании
    private String name;

}
