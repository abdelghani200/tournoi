package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.but.ButDtoReq;
import com.youcode.tournoi.dtos.but.ButDtoRes;
import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.dtos.match.MatchDtoRes;
import com.youcode.tournoi.dtos.player.PlayerDtoRes;
import com.youcode.tournoi.entities.Gool;
import com.youcode.tournoi.entities.Equipe;
import com.youcode.tournoi.entities.Match;
import com.youcode.tournoi.entities.Player;
import com.youcode.tournoi.exceptions.GoolNotFoundException;
import com.youcode.tournoi.persistence.ButRepository;
import com.youcode.tournoi.services.interfaces.ButService;
import com.youcode.tournoi.services.interfaces.EquipeService;
import com.youcode.tournoi.services.interfaces.MatchService;
import com.youcode.tournoi.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ButServiceImpl implements ButService {

    private final ButRepository butRepository;
    private final UserService playerService;
    private final EquipeService equipeService;
    private final MatchService matchService;
    private final ModelMapper modelMapper;

    @Autowired
    public ButServiceImpl(ButRepository butRepository, UserService playerService, EquipeService equipeService, MatchService matchService, ModelMapper modelMapper) {
        this.butRepository = butRepository;
        this.playerService = playerService;
        this.equipeService = equipeService;
        this.matchService = matchService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ButDtoReq saveBut(ButDtoReq butDtoReq) {
        Gool but = modelMapper.map(butDtoReq, Gool.class);

        EquipeDtoRes equipe = equipeService.findById(butDtoReq.getEquipeId());
        PlayerDtoRes player = playerService.findById(butDtoReq.getPlayerId());
        MatchDtoRes match = matchService.findById(butDtoReq.getMatchId());

        but.setEquipe(modelMapper.map(equipe, Equipe.class));
        but.setPlayer(modelMapper.map(player, Player.class));
        but.setMatch(modelMapper.map(match, Match.class));

        but = butRepository.save(but);

        return modelMapper.map(but, ButDtoReq.class);
    }



}
