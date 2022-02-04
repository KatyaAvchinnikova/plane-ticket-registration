package com.example.businesslayernew.validator;

import com.example.businesslayernew.dto.flight.FlightRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
@Slf4j
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
//            TODO: пробелы после фигурных скобок. В каком случае у тебя вообще может отработать этот кейс? Если ни в каком - зачем он?
            errors.reject("Failed target object");
        }

    }

}
