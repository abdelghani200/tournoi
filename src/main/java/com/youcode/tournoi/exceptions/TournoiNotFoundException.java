package com.youcode.tournoi.exceptions;

public class TournoiNotFoundException extends RuntimeException {
    public TournoiNotFoundException(String message) {
        super(message);
    }
}
