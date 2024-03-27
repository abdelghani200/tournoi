package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.dtos.equipe.EquipeWithPlayerDtoReq;
import com.youcode.tournoi.entities.*;
import com.youcode.tournoi.exceptions.EquipeNotFoundException;
import com.youcode.tournoi.exceptions.PlayerNotFoundException;
import com.youcode.tournoi.persistence.*;
import com.youcode.tournoi.services.interfaces.EquipeService;
import com.youcode.tournoi.services.interfaces.EquipeWithPlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipeServiceImpl implements EquipeService {

    private final EquipeRepository equipeRepository;
    private final MatchFifaRepository matchFifaRepository;
    private final MatchFBRepository matchFBRepository;
    private final ModelMapper modelMapper;
    private final EquipeWithPlayerRepository equipeWithPlayerRepository;

    public EquipeServiceImpl(EquipeRepository equipeRepository, MatchFifaRepository matchFifaRepository, MatchFBRepository matchFBRepository, ModelMapper modelMapper, EquipeWithPlayerRepository equipeWithPlayerRepository){
        this.equipeRepository = equipeRepository;
        this.matchFifaRepository = matchFifaRepository;
        this.matchFBRepository = matchFBRepository;
        this.modelMapper = modelMapper;
        this.equipeWithPlayerRepository = equipeWithPlayerRepository;
    }

    @Override
    public EquipeDtoRes save(EquipeDtoRes equipeDtoRes) {
        Equipe equipeEntity = modelMapper.map(equipeDtoRes, Equipe.class);
        Equipe savedEquipe = equipeRepository.save(equipeEntity);
        return modelMapper.map(savedEquipe, EquipeDtoRes.class);
    }

    @Override
    public List<EquipeDtoRes> getAll() {
        List<Equipe> equipeList = equipeRepository.findAll();
        return equipeList.stream()
                .map(equipe -> modelMapper.map(equipe, EquipeDtoRes.class))
                .collect(Collectors.toList());
    }

    @Override
    public EquipeDtoRes findById(Long id) {
        Equipe equipe = equipeRepository.findById(id)
                .orElseThrow(()->new EquipeNotFoundException("The equipe with ID " + id + " does not exist"));
        return modelMapper.map(equipe, EquipeDtoRes.class);
    }

    @Override
    public void delete(Long id) {
        equipeRepository.deleteById(id);
    }

    @Override
    public EquipeDtoRes update(Long id, EquipeDtoRes equipeDtoRes) {
        Optional<Equipe> existingEquipeOptional = equipeRepository.findById(id);
        if (existingEquipeOptional.isEmpty()) {
            throw new EquipeNotFoundException("Equipe not found with ID: " + id);
        }

        Equipe existingEquipe = existingEquipeOptional.get();

        existingEquipe.setNomEquipe(equipeDtoRes.getNomEquipe());
        existingEquipe.setType(equipeDtoRes.getType());

        Equipe updatedEquipe = equipeRepository.save(existingEquipe);

        return modelMapper.map(updatedEquipe, EquipeDtoRes.class);
    }

    @Override
    public EquipeWithPlayerDtoReq findByIdWithMatchStats(Long id) {
        TeamMembership equipeWithPlayer = equipeWithPlayerRepository.findById(id)
                .orElseThrow(() -> new EquipeNotFoundException("The equipe with ID " + id + " does not exist"));

        Equipe equipe = equipeWithPlayer.getEquipe();
        Player player = equipeWithPlayer.getPlayer();

        List<MatchFifa> matchsFifa = matchFifaRepository.findByPlayer1OrPlayer2(player, player);
        List<MatchFB> matchsFB = matchFBRepository.findByEquipe1OrEquipe2(equipe, equipe);

        List<Match> matchs = new ArrayList<>();
        matchs.addAll(matchsFifa);
        matchs.addAll(matchsFB);

        int nbMatchsJoues = matchs.size();
        int points = 0;
        int nbMatchsGagnes = 0;
        int nbMatchsPerdus = 0;

        for (Match match : matchs) {
            if (match instanceof MatchFB matchFB) {
                if (matchFB.getEquipe1().equals(equipe)) {
                    if (matchFB.getScoreEquipe1() > matchFB.getScoreEquipe2()) {
                        points += 3;
                        nbMatchsGagnes++;
                    } else if (matchFB.getScoreEquipe1() < matchFB.getScoreEquipe2()) {
                        nbMatchsPerdus++;
                    } else {
                        points += 1;
                    }
                } else if (matchFB.getEquipe2().equals(equipe)) {
                    if (matchFB.getScoreEquipe2() > matchFB.getScoreEquipe1()) {
                        points += 3;
                        nbMatchsGagnes++;
                    } else if (matchFB.getScoreEquipe2() < matchFB.getScoreEquipe1()) {
                        nbMatchsPerdus++;
                    } else {
                        points += 1;
                    }
                }
            } else if (match instanceof MatchFifa matchFifa) {
                if (matchFifa.getPlayer1().equals(player)) {
                    if (matchFifa.getScoreEquipe1() > matchFifa.getScoreEquipe2()) {
                        points += 3;
                        nbMatchsGagnes++;
                    } else if (matchFifa.getScoreEquipe1() < matchFifa.getScoreEquipe2()) {
                        nbMatchsPerdus++;
                    } else {
                        points += 1;
                    }
                } else if (matchFifa.getPlayer2().equals(player)) {
                    if (matchFifa.getScoreEquipe2() > matchFifa.getScoreEquipe1()) {
                        points += 3;
                        nbMatchsGagnes++;
                    } else if (matchFifa.getScoreEquipe2() < matchFifa.getScoreEquipe1()) {
                        nbMatchsPerdus++;
                    } else {
                        points += 1;
                    }
                }
            }
        }

        EquipeWithPlayerDtoReq equipeDtoRes = modelMapper.map(equipeWithPlayer, EquipeWithPlayerDtoReq.class);
        equipeDtoRes.setNbMatchsJoues(nbMatchsJoues);
        equipeDtoRes.setPoints(points);
        equipeDtoRes.setNbMatchsGagnes(nbMatchsGagnes);
        equipeDtoRes.setNbMatchsPerdus(nbMatchsPerdus);

        return equipeDtoRes;
    }


    @Override
    public List<EquipeWithPlayerDtoReq> findAllWithMatchStats() {
        List<TeamMembership> teamMemberships = equipeWithPlayerRepository.findAll();
        List<EquipeWithPlayerDtoReq> equipesWithStats = new ArrayList<>();

        for (TeamMembership teamMembership : teamMemberships) {
            EquipeWithPlayerDtoReq equipeWithStats = findByIdWithMatchStats(teamMembership.getId());
            equipesWithStats.add(equipeWithStats);
        }

        return equipesWithStats;
    }



}
