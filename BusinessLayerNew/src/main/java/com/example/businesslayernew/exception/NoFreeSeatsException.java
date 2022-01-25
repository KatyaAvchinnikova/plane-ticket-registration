package com.example.businesslayernew.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
@ResponseStatus(HttpStatus.BAD_REQUEST)
//TODO: то же замечание, что и в эксепшне выше
public class NoFreeSeatsException extends RuntimeException {
    public NoFreeSeatsException(String FlightFrom, String FlightTo, LocalDateTime flightTime) {
        super(String.format("No free seats on flight from %s to %s time departure: %s", FlightFrom,
                FlightTo, flightTime));
    }
}
