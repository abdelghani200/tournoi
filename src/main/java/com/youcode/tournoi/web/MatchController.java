package com.youcode.tournoi.web;

import com.youcode.tournoi.dtos.match.MatchDtoRes;
import com.youcode.tournoi.dtos.match.MatchFbDto;
import com.youcode.tournoi.dtos.match.MatchFbDtoRes;
import com.youcode.tournoi.dtos.match.MatchFifaDto;
import com.youcode.tournoi.services.interfaces.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<MatchFbDto> createMatchFB(@RequestBody MatchFbDto matchDto){
        MatchFbDto createdMatch = matchService.save(matchDto);
        return  new ResponseEntity<>(createdMatch, HttpStatus.CREATED);
    }

    @PostMapping("/fifa")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<MatchFifaDto> createMatchFifa(@RequestBody MatchFifaDto matchFifaDto){
        MatchFifaDto createdMatch = matchService.save(matchFifaDto);
        return new ResponseEntity<>(createdMatch, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MatchFbDtoRes>> getAllMatches() {
        List<MatchFbDtoRes> allMatches = matchService.getAll();
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
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<MatchFbDto> updateMatch(@PathVariable Long id, @RequestBody MatchFbDto matchDto) {
        MatchFbDto updatedMatch = matchService.update(id, matchDto);
        return new ResponseEntity<>(updatedMatch, HttpStatus.OK);
    }


}
