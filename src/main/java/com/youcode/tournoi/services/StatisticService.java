package com.youcode.tournoi.services;

import com.youcode.tournoi.dtos.statistic.StatisticSummary;
import com.youcode.tournoi.services.interfaces.EquipeService;
import com.youcode.tournoi.services.interfaces.MatchService;
import com.youcode.tournoi.services.interfaces.TournoiService;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

    private final TournoiService tournoiService;
    private final MatchService matchService;
    private final EquipeService equipeService;


    public StatisticService(TournoiService tournoiService, MatchService matchService, EquipeService equipeService) {
        this.tournoiService = tournoiService;
        this.matchService = matchService;
        this.equipeService = equipeService;
    }

    public int getTotalTournois(){
        return tournoiService.getAll().size();
    }

    public int getTotalMatches() {
        return matchService.getAll().size();
    }

    public int getTotalEquipes() {
        return equipeService.getAll().size();
    }


    public StatisticSummary getAllStatistics(){
        int totalTournois = tournoiService.getAll().size();
        int totalMatches = matchService.getAll().size();
        int totalEquipes = equipeService.getAll().size();

        return new StatisticSummary(totalTournois, totalMatches, totalEquipes);
    }


}
