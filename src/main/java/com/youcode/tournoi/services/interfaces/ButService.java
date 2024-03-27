package com.youcode.tournoi.services.interfaces;

import com.youcode.tournoi.dtos.but.ButDtoReq;
import com.youcode.tournoi.dtos.but.ButDtoRes;

import java.util.List;
import java.util.Map;

public interface ButService {
    ButDtoReq saveBut(ButDtoReq butDtoReq);
    List<ButDtoRes> getAll();
    int getGoalsByPlayerId(Long playerId);
    void incrementNumberOfGoals(Long idBut, Integer newNumberOfGoals);
    void decrementNumberOfGoals(Long idBut, Integer newNumberOfGoals);
    List<ButDtoRes> searchByAllAttributes(String playerName, String equipeName, String tournoiName);
    List<ButDtoRes> getGoalsByPlayerOrdered();
}
