package com.youcode.tournoi.services.interfaces;

import com.youcode.tournoi.dtos.equipe.EquipeWithPlayerDtoReq;
import com.youcode.tournoi.dtos.equipe.EquipeWithPlayerDtoRes;
import com.youcode.tournoi.exceptions.EquipeNotFoundException;

import java.util.List;

public interface EquipeWithPlayerService {
    EquipeWithPlayerDtoRes savePlayerToEquipe(EquipeWithPlayerDtoRes withPlayer) throws EquipeNotFoundException;
    List<EquipeWithPlayerDtoReq> getPlayersByEquipe(Long equipeId);
    List<EquipeWithPlayerDtoReq> getPlayersByEquipes(Long equipeId1, Long equipeId2);
}
