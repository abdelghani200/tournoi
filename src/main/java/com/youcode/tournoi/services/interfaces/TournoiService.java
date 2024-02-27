package com.youcode.tournoi.services.interfaces;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.dtos.player.EquipeToTournoi;
import com.youcode.tournoi.dtos.tournoi.TournoiDto;
import com.youcode.tournoi.dtos.tournoi.TournoiDtoRes;
import com.youcode.tournoi.entities.Tournoi;

import java.util.List;

public interface TournoiService {
    TournoiDto save(TournoiDto tournoiDto);

}
