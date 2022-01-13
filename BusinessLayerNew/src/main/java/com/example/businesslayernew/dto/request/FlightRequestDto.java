package com.example.businesslayernew.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class FlightRequestDto {

    private Date date;

    private Long airportToId;

    private Long airportFromId;

}
