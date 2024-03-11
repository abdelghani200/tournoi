package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.dtos.equipe.EquipeStatDto;
import com.youcode.tournoi.entities.Equipe;
import com.youcode.tournoi.entities.Match;
import com.youcode.tournoi.exceptions.EquipeNotFoundException;
import com.youcode.tournoi.persistence.EquipeRepository;
import com.youcode.tournoi.persistence.MatchRepository;
import com.youcode.tournoi.persistence.PlayerRepository;
import com.youcode.tournoi.services.interfaces.EquipeService;
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
    private final MatchRepository matchRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EquipeServiceImpl(EquipeRepository equipeRepository, MatchRepository matchRepository, ModelMapper modelMapper){
        this.equipeRepository = equipeRepository;
        this.matchRepository = matchRepository;
        this.modelMapper = modelMapper;
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
    public EquipeStatDto findByIdWithMatchStats(Long id) {
        Equipe equipe = equipeRepository.findById(id)
                .orElseThrow(() -> new EquipeNotFoundException("The equipe with ID " + id + " does not exist"));

        List<Match> matchs = matchRepository.findByEquipe1OrEquipe2(equipe, equipe);

        int nbMatchsJoues = matchs.size();
        int points = 0;
        int nbMatchsGagnes = 0;
        int nbMatchsPerdus = 0;

        for (Match match : matchs) {
            if (match.getEquipe1().equals(equipe)) {
                if (match.getScoreEquipe1() > match.getScoreEquipe2()) {
                    points += 3;
                    nbMatchsGagnes++;
                } else if (match.getScoreEquipe1() < match.getScoreEquipe2()) {
                    nbMatchsPerdus++;
                } else {
                    points += 1;
                }
            } else if (match.getEquipe2().equals(equipe)) {
                if (match.getScoreEquipe2() > match.getScoreEquipe1()) {
                    points += 3;
                    nbMatchsGagnes++;
                } else if (match.getScoreEquipe2() < match.getScoreEquipe1()) {
                    nbMatchsPerdus++;
                } else {
                    points += 1;
                }
            }
        }

        EquipeStatDto equipeDtoRes = modelMapper.map(equipe, EquipeStatDto.class);
        equipeDtoRes.setNbMatchsJoues(nbMatchsJoues);
        equipeDtoRes.setPoints(points);
        equipeDtoRes.setNbMatchsGagnes(nbMatchsGagnes);
        equipeDtoRes.setNbMatchsPerdus(nbMatchsPerdus);

        return equipeDtoRes;
    }


    @Override
    public List<EquipeStatDto> findAllWithMatchStats() {
        List<Equipe> equipes = equipeRepository.findAll();
        List<EquipeStatDto> equipesWithStats = new ArrayList<>();

        for (Equipe equipe : equipes) {
            EquipeStatDto equipeWithStats = findByIdWithMatchStats(equipe.getIdEquipe());
            equipesWithStats.add(equipeWithStats);
        }

        return equipesWithStats;
    }



}
