package com.youcode.tournoi.web;

import com.youcode.tournoi.dtos.carton.CartonDtoReq;
import com.youcode.tournoi.services.interfaces.CartonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carton")
public class CartonController {
    private final CartonService cartonService;

    public CartonController(CartonService cartonService) {
        this.cartonService = cartonService;
    }

    @PostMapping
    public ResponseEntity<CartonDtoReq> createEquipe(@RequestBody CartonDtoReq cartonDtoReq){
        CartonDtoReq createdCarton = cartonService.save(cartonDtoReq);
        return  new ResponseEntity<>(createdCarton, HttpStatus.CREATED);
    }
}
