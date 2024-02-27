package com.youcode.tournoi.web;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.services.interfaces.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipe")
public class EquipeController {

    private final EquipeService equipeService;

    @Autowired
    public EquipeController(EquipeService equipeService){
        this.equipeService = equipeService;
    }

    @PostMapping
    public ResponseEntity<EquipeDtoRes> createEquipe(@RequestBody EquipeDtoRes equipeDtoRes){
        EquipeDtoRes createdEquipe = equipeService.save(equipeDtoRes);
        return  new ResponseEntity<>(createdEquipe, HttpStatus.CREATED);
    }

    @GetMapping
    public List<EquipeDtoRes> getAllEquipes(){
        return equipeService.getAll();
    }

}
