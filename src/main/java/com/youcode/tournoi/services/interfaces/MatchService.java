package com.youcode.tournoi.services.interfaces;

import com.youcode.tournoi.dtos.match.MatchDto;
import com.youcode.tournoi.dtos.match.MatchDtoRes;

import java.util.List;

public interface MatchService {
    MatchDto save(MatchDto matchDto);
    List<MatchDtoRes> getAll();
    MatchDtoRes findById(Long id);
    List<MatchDtoRes> getMatchesByTournoiId(Long tournoiId);
    void delete(Long id);
    MatchDto update(Long id, MatchDto matchDto);
}
