package com.example.businesslayernew.dto.flight;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FlightRequest {

    @NotNull
    private Long airportToId;

    @NotNull
    private Long airportFromId;

    private ZonedDateTime departureTime;

    private ZonedDateTime arrivalTime;

    @Max(120)
    @Min(0)
    private int numberOfFreeSeats;
}
