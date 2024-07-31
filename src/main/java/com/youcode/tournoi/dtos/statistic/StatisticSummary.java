package com.youcode.tournoi.dtos.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatisticSummary {
    private int totalTournois;
    private int totalMatches;
    private int totalEquipes;
    private int totalPlayers;

}
