package com.example.businesslayernew.controller;

import com.example.businesslayernew.exception.AppException;
import org.jetbrains.annotations.NotNull;
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

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            AppException.class,
            RuntimeException.class
    })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        HttpStatus status = HttpStatus.NOT_FOUND;

        if(ex instanceof AppException){
            status = ((AppException) ex).getStatus();
        }

        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), status, request);
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                          @NotNull HttpHeaders headers,
                                                                          @NotNull HttpStatus status,
                                                                          @NotNull WebRequest request) {
        String responseBody = ex.getBindingResult().getFieldErrors().stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .collect(Collectors.joining(", "));

        return handleExceptionInternal(ex, responseBody, headers, status, request);
    }
}
