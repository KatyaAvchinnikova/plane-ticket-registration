package com.example.businesslayernew.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
//TODO: Что ты выигрываешь, плодя кучу эксепшнов на каждый чих? Чем не подходит Runtime или один кастомный аля AppException на все с разными сообщениями и заданным хттп-кодом?
public class NoUserEmailException extends RuntimeException {
    public NoUserEmailException(String email) {
        super(String.format("Email %s is not found", email));
    }
}
