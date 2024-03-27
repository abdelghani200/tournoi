package com.youcode.tournoi.web;

import com.youcode.tournoi.dtos.equipe.EquipeWithPlayerDtoReq;
import com.youcode.tournoi.dtos.equipe.EquipeWithPlayerDtoRes;
import com.youcode.tournoi.dtos.player.PlayerDtoRes;
import com.youcode.tournoi.services.interfaces.EquipeWithPlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipeWithPlayer")
public class EquipeWithPlayerController {

    private final EquipeWithPlayerService withPlayerService;

    public EquipeWithPlayerController(EquipeWithPlayerService withPlayerService) {
        this.withPlayerService = withPlayerService;
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<EquipeWithPlayerDtoRes> savePlayerToEquipe(@RequestBody EquipeWithPlayerDtoRes withPlayer){
        EquipeWithPlayerDtoRes equipeWithPlayer = withPlayerService.savePlayerToEquipe(withPlayer);
        return  new ResponseEntity<>(equipeWithPlayer, HttpStatus.CREATED);
    }

    @GetMapping("/{equipeId}/players")
    public List<EquipeWithPlayerDtoReq> getPlayersOfEquipe(@PathVariable Long equipeId) {
        return withPlayerService.getPlayersByEquipe(equipeId);
    }

    @GetMapping("/playersOfTwoEquipes/{equipeId1}/{equipeId2}")
    public List<EquipeWithPlayerDtoReq> getPlayersOfTwoEquipes(@PathVariable Long equipeId1, @PathVariable Long equipeId2) {
        return withPlayerService.getPlayersByEquipes(equipeId1, equipeId2);
    }


}
