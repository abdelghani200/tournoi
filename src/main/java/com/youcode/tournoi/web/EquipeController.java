package com.youcode.tournoi.web;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.dtos.equipe.EquipeStatDto;
import com.youcode.tournoi.dtos.match.MatchDto;
import com.youcode.tournoi.dtos.player.PlayerDto;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipe(@PathVariable Long id) {
        equipeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipeDtoRes> updateEquipe(@PathVariable Long id, @RequestBody EquipeDtoRes equipeDtoRes) {
        EquipeDtoRes updatedEquipe = equipeService.update(id, equipeDtoRes);
        return new ResponseEntity<>(updatedEquipe, HttpStatus.OK);
    }

    @GetMapping("/{id}/stats")
    public ResponseEntity<EquipeStatDto> getEquipeStats(@PathVariable Long id) {
        EquipeStatDto equipeStats = equipeService.findByIdWithMatchStats(id);
        return new ResponseEntity<>(equipeStats, HttpStatus.OK);
    }

    @GetMapping("/stats")
    public List<EquipeStatDto> getAllStats(){
        return equipeService.findAllWithMatchStats();
    }

}
