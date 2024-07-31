package com.youcode.tournoi.services.interfaces;

import com.youcode.tournoi.dtos.match.*;

import java.util.List;

public interface MatchService {
    MatchFbDto save(MatchFbDto matchFbDto);
    MatchFifaDto save(MatchFifaDto matchFifaDto);
    List<MatchFbDtoRes> getAll();
    List<MatchFifaDtoRes> getAllFifa();
    MatchFbDtoRes findById(Long id);
    void delete(Long id);
    MatchFbDto update(Long id, MatchFbDto matchDto);
    List<MatchFbDtoRes> getMatchesFbByTournoiId(Long tournoiId);
    List<MatchFifaDtoRes> getMatchesFifaByTournoi(Long tournoiId);
    List<MatchDtoRes> getMatchesByTournoiId(Long tournoiId);
}
