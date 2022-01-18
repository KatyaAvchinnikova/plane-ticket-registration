package com.example.businesslayernew.dto.flight;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class FlightRequest {
//    TODO: ZonedDateTime
    private Date date;

    private Long airportToId;

    private Long airportFromId;

}
