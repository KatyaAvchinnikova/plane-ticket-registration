package com.example.businesslayernew.exception;

public class UserBadCredentialsException extends RuntimeException{
    public UserBadCredentialsException(String message) {
        super(message);
    }


}
