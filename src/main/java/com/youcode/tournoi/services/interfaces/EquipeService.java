package com.youcode.tournoi.services.interfaces;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.dtos.equipe.EquipeStatDto;
import com.youcode.tournoi.dtos.equipe.EquipeWithPlayerDtoReq;

import java.util.List;

public interface EquipeService {
    EquipeDtoRes save(EquipeDtoRes equipeDtoRes);
    List<EquipeDtoRes> getAll();
    EquipeDtoRes findById(Long id);
    void delete(Long id);
    EquipeDtoRes update(Long id, EquipeDtoRes equipeDtoRes);
    EquipeWithPlayerDtoReq findByIdWithMatchStats(Long id);
    List<EquipeWithPlayerDtoReq> findAllWithMatchStats();
}

