package com.youcode.tournoi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GoolNotFoundException extends RuntimeException{
    public GoolNotFoundException(String message){
        super(message);
    }
}
