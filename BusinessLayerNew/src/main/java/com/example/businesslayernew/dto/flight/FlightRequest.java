package com.example.businesslayernew.dto.flight;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FlightRequest {
//TODO: В чем смысл кастомных месседжей?
//Для отправки релевантного сообщения об ошибке на фронт
    @NotNull(message = "Airport to id cannot be empty")
    private Long airportToId;
    @NotNull(message = "Airport from id cannot be empty")
    private Long airportFromId;
    private ZonedDateTime departureTime;
    private ZonedDateTime arrivalTime;
    @Max(120)
    @Min(0)
    private int numberOfFreeSeats;
}
