package com.youcode.tournoi.web;


import com.youcode.tournoi.dtos.statistic.StatisticSummary;
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


    @GetMapping("/summary")
    public StatisticSummary getStatisticsSummary() {
        return statisticService.getAllStatistics();
    }
}
