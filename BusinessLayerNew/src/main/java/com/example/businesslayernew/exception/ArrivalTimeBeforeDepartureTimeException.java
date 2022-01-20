package com.example.businesslayernew.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ArrivalTimeBeforeDepartureTimeException extends RuntimeException {

    public ArrivalTimeBeforeDepartureTimeException(String airportFrom, String airportTo) {
        super(String.format("For flight from %s to %s arrival time should be after departure time"
                , airportFrom, airportTo));
    }

}
