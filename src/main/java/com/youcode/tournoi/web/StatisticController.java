package com.youcode.tournoi.web;


import com.youcode.tournoi.services.StatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/totalTournois")
    public ResponseEntity<Integer> getTotalTournois() {
        int totalTournois = statisticService.getTotalTournois();
        return ResponseEntity.ok(totalTournois);
    }

    @GetMapping("/totalMatches")
    public ResponseEntity<Integer> getTotalMatches() {
        int totalMatches = statisticService.getTotalMatches();
        return ResponseEntity.ok(totalMatches);
    }

    @GetMapping("/totalEquipes")
    public ResponseEntity<Integer> getTotalEquipes() {
        int totalEquipes = statisticService.getTotalEquipes();
        return ResponseEntity.ok(totalEquipes);
    }
}
