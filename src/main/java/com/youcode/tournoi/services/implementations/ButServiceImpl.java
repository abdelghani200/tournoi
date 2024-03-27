package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.but.ButDtoReq;
import com.youcode.tournoi.dtos.but.ButDtoRes;
import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.dtos.match.MatchFbDto;
import com.youcode.tournoi.dtos.match.MatchFbDtoRes;
import com.youcode.tournoi.dtos.match.MatchFifaDto;
import com.youcode.tournoi.dtos.player.PlayerDto;
import com.youcode.tournoi.dtos.player.PlayerDtoRes;
import com.youcode.tournoi.entities.*;
import com.youcode.tournoi.enums.TypeTournoi;
import com.youcode.tournoi.exceptions.GoolNotFoundException;
import com.youcode.tournoi.persistence.ButRepository;
import com.youcode.tournoi.services.interfaces.ButService;
import com.youcode.tournoi.services.interfaces.EquipeService;
import com.youcode.tournoi.services.interfaces.MatchService;
import com.youcode.tournoi.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        MatchFbDtoRes match = matchService.findById(butDtoReq.getMatchId());

        but.setEquipe(modelMapper.map(equipe, Equipe.class));
        but.setPlayer(modelMapper.map(player, Player.class));
        but.setMatch(modelMapper.map(match, Match.class));

        but = butRepository.save(but);

        Match matchEntity = modelMapper.map(match, Match.class);
        Equipe equipeEntity = modelMapper.map(equipe, Equipe.class);

        if (matchEntity.getTournoi().getType() != TypeTournoi.Fifa) {
            MatchFB matchFB = modelMapper.map(match, MatchFB.class);
            if (matchFB.getEquipe1() != null && matchFB.getEquipe1().getIdEquipe().equals(equipeEntity.getIdEquipe())) {
                matchFB.setScoreEquipe1(matchFB.getScoreEquipe1() + 1);
            } else if (matchFB.getEquipe2() != null && matchFB.getEquipe2().getIdEquipe().equals(equipeEntity.getIdEquipe())) {
                matchFB.setScoreEquipe2(matchFB.getScoreEquipe2() + 1);
            }
            matchService.update(matchFB.getIdMatch(), modelMapper.map(matchFB, MatchFbDto.class));
        }

        return modelMapper.map(but, ButDtoReq.class);
    }



    @Override
    public List<ButDtoRes> getAll() {
        List<Gool> butList = butRepository.findAll();
        return butList.stream()
                .map(equipe -> modelMapper.map(equipe, ButDtoRes.class))
                .collect(Collectors.toList());
    }

    @Override
    public int getGoalsByPlayerId(Long playerId) {
        return butRepository.countByPlayerId(playerId);
    }

    @Override
    public void incrementNumberOfGoals(Long idBut, Integer newNumberOfGoals) {
        Gool butToUpdate = butRepository.findById(idBut).orElseThrow(() -> new GoolNotFoundException("Gool not found with id: " + idBut));
        Integer currentNumberOfGoals = butToUpdate.getNumberOfGoal();
        Integer updatedNumberOfGoals = currentNumberOfGoals + newNumberOfGoals;
        butToUpdate.setNumberOfGoal(updatedNumberOfGoals);
        butRepository.save(butToUpdate);
    }

    @Override
    public void decrementNumberOfGoals(Long idBut, Integer newNumberOfGoals) {
        Gool butToUpdate = butRepository.findById(idBut).orElseThrow(() -> new GoolNotFoundException("Gool not found with id: " + idBut));
        int currentNumberOfGoals = butToUpdate.getNumberOfGoal();
        int updatedNumberOfGoals = currentNumberOfGoals - newNumberOfGoals;
        butToUpdate.setNumberOfGoal(updatedNumberOfGoals);
        butRepository.save(butToUpdate);
    }

    @Override
    public List<ButDtoRes> searchByAllAttributes(String playerName, String equipeName, String tournoiName) {
        List<Gool> butList = butRepository.findAll();

        return butList.stream()
                .filter(but -> playerName == null || but.getPlayer().getNomUser().equals(playerName))
                .filter(but -> equipeName == null || but.getEquipe().getNomEquipe().equals(equipeName))
                .filter(but -> tournoiName == null || but.getMatch().getTournoi().getNameTournoi().equals(tournoiName))
                .map(but -> modelMapper.map(but, ButDtoRes.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ButDtoRes> getGoalsByPlayerOrdered() {
        List<Object[]> results = butRepository.findGoalsByPlayerOrdered();
        List<ButDtoRes> goalsByPlayer = new ArrayList<>();

        for (Object[] result : results) {
            String playerName = (String) result[0];
            Integer goals = ((Number) result[1]).intValue();

            ButDtoRes butDtoRes = new ButDtoRes();
            butDtoRes.setPlayer(new PlayerDto(playerName));
            butDtoRes.setNumberOfGoal(goals);

            goalsByPlayer.add(butDtoRes);
        }

        return goalsByPlayer;
    }



}
