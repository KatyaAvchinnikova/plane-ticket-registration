package com.example.businesslayernew.validator;

import com.example.businesslayernew.dto.flight.FlightRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

public class FlightTimeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return FlightRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof FlightRequest ){
            FlightRequest flightRequest = (FlightRequest) target;
            LocalDateTime timeArrival = flightRequest.getArrivalTime().toLocalDateTime();
            LocalDateTime timeDeparture = flightRequest.getDepartureTime().toLocalDateTime();
            if (timeDeparture.isAfter(timeArrival)) {
                errors.reject("Time departure should be before time arrival");
            }
        }else{
            errors.reject("Failed target object");
        }

    }

}
