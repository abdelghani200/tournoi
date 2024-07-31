package com.youcode.tournoi.services.interfaces;

import com.youcode.tournoi.dtos.player.EquipeToTournoi;
import com.youcode.tournoi.dtos.tournoi.TournoiDto;
import com.youcode.tournoi.dtos.tournoi.TournoiDtoRes;

import java.util.List;

public interface TournoiService {
    TournoiDto save(TournoiDto tournoiDto);
    List<TournoiDtoRes> getAll();
    EquipeToTournoi inscrireEquipeDansTournoi(EquipeToTournoi equipeToTournoi);
    void delete(Long id);
    TournoiDtoRes update(Long id, TournoiDtoRes dtoRes);
}
