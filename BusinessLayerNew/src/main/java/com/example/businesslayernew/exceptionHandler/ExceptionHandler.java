package com.example.businesslayernew.exceptionHandler;

import com.example.businesslayernew.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

//TODO: перенести в пакет контроллер
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({
            ResourceNotFoundException.class, NoFreeSeatsException.class,
            ArrivalTimeBeforeDepartureTimeException.class,
            NoUserEmailException.class,
            UserBadCredentialsException.class
    })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> onValidationException(ConstraintViolationException ex,
                                                           WebRequest request) {
        StringBuilder bodyOfResponse = new StringBuilder();
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            bodyOfResponse.append(violation.getPropertyPath())
                    .append(violation.getMessage());
        }
        return handleExceptionInternal(ex, bodyOfResponse.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
                request);
    }

}
