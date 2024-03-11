package com.youcode.tournoi.services.interfaces;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.dtos.equipe.EquipeStatDto;
import com.youcode.tournoi.dtos.match.MatchDto;
import com.youcode.tournoi.dtos.player.PlayerDto;
import com.youcode.tournoi.exceptions.EquipeNotFoundException;

import java.util.List;

public interface EquipeService {
    EquipeDtoRes save(EquipeDtoRes equipeDtoRes);
    List<EquipeDtoRes> getAll();
    EquipeDtoRes findById(Long id);
    void delete(Long id);
    EquipeDtoRes update(Long id, EquipeDtoRes equipeDtoRes);
    EquipeStatDto findByIdWithMatchStats(Long id);
    List<EquipeStatDto> findAllWithMatchStats();
}

