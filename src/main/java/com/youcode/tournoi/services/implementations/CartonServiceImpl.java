package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.carton.CartonDtoRes;
import com.youcode.tournoi.entities.*;
import com.youcode.tournoi.persistence.CartonRepository;
import com.youcode.tournoi.persistence.MatchFBRepository;
import com.youcode.tournoi.persistence.MatchFifaRepository;
import com.youcode.tournoi.persistence.PlayerRepository;
import com.youcode.tournoi.services.interfaces.CartonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartonServiceImpl implements CartonService {

    private final CartonRepository cartonRepository;
    private final ModelMapper modelMapper;
    private final MatchFBRepository matchFBRepository;
    private final MatchFifaRepository matchFifaRepository;
    private final PlayerRepository playerRepository;

    public CartonServiceImpl(CartonRepository cartonRepository, ModelMapper modelMapper, MatchFBRepository matchFBRepository, MatchFifaRepository matchFifaRepository, PlayerRepository playerRepository) {
        this.cartonRepository = cartonRepository;
        this.modelMapper = modelMapper;
        this.matchFBRepository = matchFBRepository;
        this.matchFifaRepository = matchFifaRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public CartonDtoRes save(CartonDtoRes cartonDtoRes) {

        Optional<MatchFifa> matchFifaOptional = matchFifaRepository.findById(cartonDtoRes.getMatchId());
        Optional<MatchFB> matchFbOptional = matchFBRepository.findById(cartonDtoRes.getMatchId());

        // Vérifier si le match est un match FIFA
        if (matchFifaOptional.isPresent()) {
            MatchFifa matchFifa = matchFifaOptional.get();
            Carton cartonEntity = modelMapper.map(cartonDtoRes, Carton.class);
            cartonEntity.setMatch(matchFifa);
            Carton savedCarton = cartonRepository.save(cartonEntity);
            return modelMapper.map(savedCarton, CartonDtoRes.class);
        } else if (matchFbOptional.isPresent()) {
            MatchFB matchFb = matchFbOptional.get();
            Carton cartonEntity = modelMapper.map(cartonDtoRes, Carton.class);
            cartonEntity.setMatch(matchFb);
            Carton savedCarton = cartonRepository.save(cartonEntity);
            return modelMapper.map(savedCarton, CartonDtoRes.class);
        } else {
            throw new IllegalArgumentException("L'identifiant du match spécifié n'existe pas");
        }
    }






}
