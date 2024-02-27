package com.youcode.tournoi.services.interfaces;

import com.youcode.tournoi.dtos.equipe.EquipeWithPlayerDtoRes;
import com.youcode.tournoi.exceptions.EquipeNotFoundException;

public interface EquipeWithPlayerService {
    EquipeWithPlayerDtoRes savePlayerToEquipe(EquipeWithPlayerDtoRes withPlayer) throws EquipeNotFoundException;
}
