package com.youcode.tournoi.services.interfaces;


import com.youcode.tournoi.dtos.carton.CartonDtoReq;
import com.youcode.tournoi.dtos.carton.CartonDtoRes;

public interface CartonService {
    CartonDtoRes save(CartonDtoRes cartonDtoReq);


}
