package com.example.businesslayernew.exception;

import com.example.businesslayernew.exception.ErrorResponse.ValidationErrorResponse;
import com.example.businesslayernew.exception.ErrorResponse.Violation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({
            ResourceNotFoundException.class, NoFreeSeatsException.class,
            ArrivalTimeBeforeDepartureTimeException.class
    })
    protected ResponseEntity<Object> handleConflict(ResourceNotFoundException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> onValidationException(ConstraintViolationException ex,
            WebRequest request) {
        String bodyOfResponse = "";
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            bodyOfResponse = violation.getPropertyPath().toString() + " " + violation.getMessage();
        }
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
