package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.dtos.match.MatchDto;
import com.youcode.tournoi.dtos.match.MatchDtoRes;
import com.youcode.tournoi.entities.Equipe;
import com.youcode.tournoi.entities.Match;
import com.youcode.tournoi.exceptions.EquipeNotFoundException;
import com.youcode.tournoi.persistence.MatchRepository;
import com.youcode.tournoi.services.interfaces.MatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, ModelMapper modelMapper) {
        this.matchRepository = matchRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MatchDto save(MatchDto matchDto) {
        Match matchEntity = modelMapper.map(matchDto, Match.class);
        Match savedMatch = matchRepository.save(matchEntity);
        return modelMapper.map(savedMatch, MatchDto.class);
    }

    @Override
    public List<MatchDtoRes> getAll() {
        List<Match> matchList = matchRepository.findAll();
        return matchList.stream()
                .map(match -> modelMapper.map(match, MatchDtoRes.class))
                .collect(Collectors.toList());
    }



}
