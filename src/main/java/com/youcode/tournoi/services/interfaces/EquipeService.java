package com.youcode.tournoi.services.interfaces;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.exceptions.EquipeNotFoundException;

import java.util.List;

public interface EquipeService {
    EquipeDtoRes save(EquipeDtoRes equipeDtoRes);
    List<EquipeDtoRes> getAll();


}

