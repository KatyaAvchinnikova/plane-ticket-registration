package com.example.businesslayernew.dto.flight;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Setter
public class FlightResponse {
    private LocalDateTime arrivalTime;

    private LocalDateTime departureTime;

    private String airportTo;

    private String airportFrom;

    private int numberOfFreeSeats;
}
