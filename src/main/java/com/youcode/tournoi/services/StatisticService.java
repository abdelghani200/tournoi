package com.youcode.tournoi.services;

import com.youcode.tournoi.dtos.statistic.StatisticSummary;
import com.youcode.tournoi.services.interfaces.EquipeService;
import com.youcode.tournoi.services.interfaces.MatchService;
import com.youcode.tournoi.services.interfaces.TournoiService;
import com.youcode.tournoi.services.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

    private final TournoiService tournoiService;
    private final MatchService matchService;
    private final EquipeService equipeService;
    private final UserService userService;


    public StatisticService(TournoiService tournoiService, MatchService matchService, EquipeService equipeService, UserService userService) {
        this.tournoiService = tournoiService;
        this.matchService = matchService;
        this.equipeService = equipeService;
        this.userService = userService;
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

    public int getTotalPlayers(){
        return userService.getAllPlayers().size();
    }


    public StatisticSummary getAllStatistics(){
        int totalTournois = this.getTotalTournois();
        int totalMatches = this.getTotalMatches();
        int totalEquipes = this.getTotalEquipes();
        int totalPlayers = this.getTotalPlayers();

        return new StatisticSummary(totalTournois, totalMatches, totalEquipes, totalPlayers);
    }


}
