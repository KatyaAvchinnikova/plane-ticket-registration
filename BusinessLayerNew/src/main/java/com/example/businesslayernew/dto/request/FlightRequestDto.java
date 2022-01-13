package com.example.businesslayernew.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class FlightRequestDto {

    private Long id;

    private Date date;

    private Long airportTo;

    private Long airportFrom;

}
