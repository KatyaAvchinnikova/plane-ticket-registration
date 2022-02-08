package com.example.businesslayernew.controller;

import com.example.businesslayernew.exception.ArrivalTimeBeforeDepartureTimeException;
import com.example.businesslayernew.exception.NoFreeSeatsException;
import com.example.businesslayernew.exception.NoUserEmailException;
import com.example.businesslayernew.exception.NotValidTokenException;
import com.example.businesslayernew.exception.ResourceNotFoundException;
import com.example.businesslayernew.exception.UserBadCredentialsException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            ResourceNotFoundException.class, NoFreeSeatsException.class,
            ArrivalTimeBeforeDepartureTimeException.class,
            NoUserEmailException.class,
            UserBadCredentialsException.class,
            NotValidTokenException.class
    })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> onValidationException(ConstraintViolationException ex,
            WebRequest request) {
        String responseBody = ex.getConstraintViolations().stream()
                                .map(e -> String.join(", ", e.getPropertyPath().toString(), e.getMessage()))
                                .collect(Collectors.joining("; "));

        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        String responseBody = ex.getBindingResult().getFieldErrors().stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .collect(Collectors.joining(", "));

        return handleExceptionInternal(ex, responseBody, headers, status, request);
    }

}
