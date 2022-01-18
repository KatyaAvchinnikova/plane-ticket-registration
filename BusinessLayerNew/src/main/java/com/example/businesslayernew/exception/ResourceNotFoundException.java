package com.example.businesslayernew.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException{
//TODO: зачем это поле?
    //private static final long serialVersionUID = 1L;
//TODO: в чем необходимост ькастомных полей в эксепшне?
//    private String resourceName;
//    private String fieldName;
//    private Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
//        TODO: "".formatted(...)
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));

    }



}
