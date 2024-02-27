package com.youcode.tournoi.web;

import com.youcode.tournoi.dtos.equipe.EquipeWithPlayerDtoRes;
import com.youcode.tournoi.services.interfaces.EquipeWithPlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/equipeWithPlayer")
public class EquipeWithPlayerController {

    private final EquipeWithPlayerService withPlayerService;

    public EquipeWithPlayerController(EquipeWithPlayerService withPlayerService) {
        this.withPlayerService = withPlayerService;
    }

    @PostMapping
    public ResponseEntity<EquipeWithPlayerDtoRes> savePlayerToEquipe(@RequestBody EquipeWithPlayerDtoRes withPlayer){
        EquipeWithPlayerDtoRes equipeWithPlayer = withPlayerService.savePlayerToEquipe(withPlayer);
        return  new ResponseEntity<>(equipeWithPlayer, HttpStatus.CREATED);
    }

}
