package com.innowise.businesslayer.validator;

import com.innowise.businesslayer.dto.flight.FlightRequest;
import com.innowise.businesslayer.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
@Slf4j
public class FlightTimeValidator implements Validator {

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return FlightRequest.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        FlightRequest flightRequest = (FlightRequest) target;
        LocalDateTime timeArrival = flightRequest.getArrivalTime().toLocalDateTime();
        LocalDateTime timeDeparture = flightRequest.getDepartureTime().toLocalDateTime();
        if (timeDeparture.isAfter(timeArrival)) {
            errors.rejectValue("departureTime", "400", "Time departure should be before time arrival");
        }
    }
}
