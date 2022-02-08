package com.example.businesslayernew.dto.flight;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FlightDto {

    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private String airportTo;
    private String airportFrom;
    private int numberOfFreeSeats;
}
