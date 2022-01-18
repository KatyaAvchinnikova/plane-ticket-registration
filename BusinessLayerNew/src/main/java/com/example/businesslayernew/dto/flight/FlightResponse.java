package com.example.businesslayernew.dto.flight;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FlightResponse {

    private Date date;

    private String airportTo;

    private String airportFrom;

}
