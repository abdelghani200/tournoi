package com.youcode.tournoi.config;

import com.youcode.tournoi.exceptions.EquipeNotFoundException;
import com.youcode.tournoi.exceptions.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public ResponseEntity<Map<String, String>> handlePlayerNotFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(createErrorMap("Player not found", e.getMessage()));
    }


    @ExceptionHandler(EquipeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, String>> handleEquipeNotFoundException(EquipeNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(createErrorMap("Equipe not found", e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createErrorMap("Internal Server Error", e.getMessage()));
    }


    private Map<String, String> createErrorMap(String errorType, String errorMessage) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorType", errorType);
        errorMap.put("errorMessage", errorMessage);
        return errorMap;
    }


}
