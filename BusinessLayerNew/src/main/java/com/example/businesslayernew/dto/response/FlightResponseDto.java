package com.example.businesslayernew.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FlightResponseDto {

    private Date date;

    private Long airportTo;

    private Long airportFrom;

}
