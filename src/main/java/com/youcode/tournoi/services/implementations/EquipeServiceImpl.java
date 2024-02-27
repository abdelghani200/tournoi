package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.entities.Equipe;
import com.youcode.tournoi.exceptions.EquipeNotFoundException;
import com.youcode.tournoi.persistence.EquipeRepository;
import com.youcode.tournoi.persistence.PlayerRepository;
import com.youcode.tournoi.services.interfaces.EquipeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipeServiceImpl implements EquipeService {

    private final EquipeRepository equipeRepository;
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EquipeServiceImpl(EquipeRepository equipeRepository, PlayerRepository playerRepository, ModelMapper modelMapper){
        this.equipeRepository = equipeRepository;
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EquipeDtoRes save(EquipeDtoRes equipeDtoRes) {
        Equipe equipeEntity = modelMapper.map(equipeDtoRes, Equipe.class);
        Equipe savedEquipe = equipeRepository.save(equipeEntity);
        return modelMapper.map(savedEquipe, EquipeDtoRes.class);
    }



}
