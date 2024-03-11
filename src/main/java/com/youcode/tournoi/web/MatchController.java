package com.youcode.tournoi.web;

import com.youcode.tournoi.dtos.match.MatchDetailsDto;
import com.youcode.tournoi.dtos.match.MatchDto;
import com.youcode.tournoi.dtos.match.MatchDtoRes;
import com.youcode.tournoi.services.interfaces.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public ResponseEntity<MatchDto> createEquipe(@RequestBody MatchDto matchDto){
        MatchDto createdMatch = matchService.save(matchDto);
        return  new ResponseEntity<>(createdMatch, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MatchDtoRes>> getAllMatches() {
        List<MatchDtoRes> allMatches = matchService.getAll();
        return new ResponseEntity<>(allMatches, HttpStatus.OK);
    }

    @GetMapping("/{tournoiId}/matches")
    public ResponseEntity<List<MatchDtoRes>> getMatchesByTournoiId(@PathVariable Long tournoiId) {
        List<MatchDtoRes> matches = matchService.getMatchesByTournoiId(tournoiId);
        if (matches.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchDto> updateMatch(@PathVariable Long id, @RequestBody MatchDto matchDto) {
        MatchDto updatedMatch = matchService.update(id, matchDto);
        return new ResponseEntity<>(updatedMatch, HttpStatus.OK);
    }


}
