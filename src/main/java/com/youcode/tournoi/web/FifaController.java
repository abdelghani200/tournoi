package com.youcode.tournoi.web;

import com.youcode.tournoi.dtos.player.EquipeToTournoi;
import com.youcode.tournoi.dtos.tournoi.FifaDto;
import com.youcode.tournoi.dtos.tournoi.TournoiDto;
import com.youcode.tournoi.dtos.tournoi.TournoiDtoRes;
import com.youcode.tournoi.services.interfaces.TournoiFifaService;
import com.youcode.tournoi.services.interfaces.TournoiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournoi/fifa")
public class FifaController {


    private final TournoiFifaService tournoiFifaService;

    public FifaController(TournoiFifaService tournoiFifaService) {
        this.tournoiFifaService = tournoiFifaService;
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<FifaDto> createAdmin(@RequestBody FifaDto fifaDto) {
        FifaDto createdfifa = tournoiFifaService.playerToFifa(fifaDto);
        return new ResponseEntity<>(createdfifa, HttpStatus.CREATED);
    }

}
