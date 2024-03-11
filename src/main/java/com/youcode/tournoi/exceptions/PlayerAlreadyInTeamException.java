package com.youcode.tournoi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayerAlreadyInTeamException extends RuntimeException{

    public PlayerAlreadyInTeamException(String message){
        super(message);
    }
}
