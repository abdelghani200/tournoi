package com.youcode.tournoi.web;

import com.youcode.tournoi.dtos.but.ButDtoReq;
import com.youcode.tournoi.dtos.but.ButDtoRes;
import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.exceptions.GoolNotFoundException;
import com.youcode.tournoi.services.interfaces.ButService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/but")
public class ButController {

    private final ButService butService;

    public ButController(ButService butService) {
        this.butService = butService;
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<ButDtoReq> createEquipe(@RequestBody ButDtoReq butDtoReq){
        ButDtoReq createdBut = butService.saveBut(butDtoReq);
        return  new ResponseEntity<>(createdBut, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('Admin')")
    public List<ButDtoRes> getAllGools(){
        return butService.getAll();
    }

    @PutMapping("/{idBut}/IncrementGoals")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> IncrementNumberOfGoals(@PathVariable Long idBut, @RequestBody Map<String, Integer> requestBody) {
        Integer newNumberOfGoals = requestBody.get("newNumberOfGoals");
        butService.incrementNumberOfGoals(idBut, newNumberOfGoals);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{idBut}/DecrementGoals")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> DecrementNumberOfGoals(@PathVariable Long idBut, @RequestBody Map<String, Integer> requestBody) {
        Integer newNumberOfGoals = requestBody.get("newNumberOfGoals");
        butService.decrementNumberOfGoals(idBut, newNumberOfGoals);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<ButDtoRes> searchButs(@RequestParam(required = false) String playerName,
                                      @RequestParam(required = false) String equipeName,
                                      @RequestParam(required = false) String tournoiName) {
        return butService.searchByAllAttributes(playerName, equipeName, tournoiName);
    }

    @GetMapping("/goalsByPlayerOrdered")
    public ResponseEntity<List<ButDtoRes>> getGoalsByPlayerOrdered() {
        List<ButDtoRes> goalsByPlayer = butService.getGoalsByPlayerOrdered();
        return ResponseEntity.ok().body(goalsByPlayer);
    }



}
