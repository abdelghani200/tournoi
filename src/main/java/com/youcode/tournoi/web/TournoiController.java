package com.youcode.tournoi.web;

import com.youcode.tournoi.dtos.player.EquipeToTournoi;
import com.youcode.tournoi.dtos.tournoi.TournoiDto;
import com.youcode.tournoi.dtos.tournoi.TournoiDtoRes;
import com.youcode.tournoi.entities.Tournoi;
import com.youcode.tournoi.services.interfaces.TournoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournoi")
public class TournoiController {

    private final TournoiService tournoiService;

    @Autowired
    public TournoiController(TournoiService tournoiService) {
        this.tournoiService = tournoiService;
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<TournoiDto> createAdmin(@RequestBody TournoiDto tournoiDto) {
        TournoiDto createdTournoi = tournoiService.save(tournoiDto);
        return new ResponseEntity<>(createdTournoi, HttpStatus.CREATED);
    }
    @GetMapping
    @PreAuthorize("hasRole('Admin') or hasRole('Player')")
    public List<TournoiDtoRes> getAllTournois(){
        return tournoiService.getAll();
    }

    @PostMapping("/equipeToTournoi")
    public ResponseEntity<EquipeToTournoi> inscrireEquipeDansTournoi(@RequestBody EquipeToTournoi equipeToTournoi) {
        EquipeToTournoi result = tournoiService.inscrireEquipeDansTournoi(equipeToTournoi);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
