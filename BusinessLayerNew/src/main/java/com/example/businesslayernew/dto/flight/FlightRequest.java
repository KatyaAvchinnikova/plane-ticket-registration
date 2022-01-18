package com.example.businesslayernew.dto.flight;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FlightRequest {
//    TODO: ZonedDateTime
    private Long airportToId;

    private Long airportFromId;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private int numberOfFreeSeats;
}
