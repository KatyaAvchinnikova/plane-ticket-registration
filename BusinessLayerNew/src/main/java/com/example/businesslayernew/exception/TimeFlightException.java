package com.example.businesslayernew.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
@Setter
@Getter
public class TimeFlightException extends RuntimeException {

    public TimeFlightException(String airportFrom, String airportTo) {
        super(String.format("For flight from %s to %s arrival time should be after departure time"
                , airportFrom, airportTo));
    }

}
