package com.example.businesslayernew.exception;

import java.time.LocalDateTime;

public class NoFreeSeatsException extends RuntimeException {
    public NoFreeSeatsException(String FlightFrom, String FlightTo, LocalDateTime flightTime) {
        super(String.format("No free seats on flight from %s to %s time departure: %s", FlightFrom,
                FlightTo, flightTime));
    }
}
