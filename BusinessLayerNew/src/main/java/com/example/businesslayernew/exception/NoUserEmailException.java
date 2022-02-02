package com.example.businesslayernew.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoUserEmailException extends RuntimeException {
    public NoUserEmailException(String email) {
        super(String.format("Email %s is not found", email));
    }
}
