package com.youcode.tournoi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EquipeNotFoundException extends RuntimeException{

    public EquipeNotFoundException(String message){
        super(message);
    }

}
