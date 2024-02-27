package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.player.EquipeToTournoi;
import com.youcode.tournoi.dtos.tournoi.TournoiDto;
import com.youcode.tournoi.dtos.tournoi.TournoiDtoRes;
import com.youcode.tournoi.entities.Equipe;
import com.youcode.tournoi.entities.Tournoi;
import com.youcode.tournoi.exceptions.TournoiNotFoundException;
import com.youcode.tournoi.persistence.EquipeRepository;
import com.youcode.tournoi.persistence.TournoiRepository;
import com.youcode.tournoi.services.interfaces.TournoiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournoiServiceImpl implements TournoiService {

    private final TournoiRepository tournoiRepository;
    private final ModelMapper modelMapper;
    private final EquipeRepository equipeRepository;

    @Autowired
    public TournoiServiceImpl(TournoiRepository tournoiRepository, ModelMapper modelMapper, EquipeRepository equipeRepository) {
        this.tournoiRepository = tournoiRepository;
        this.modelMapper = modelMapper;
        this.equipeRepository = equipeRepository;
    }

    @Override
    public TournoiDto save(TournoiDto tournoiDto) {
        Tournoi tournoiEntity = modelMapper.map(tournoiDto, Tournoi.class);
        Tournoi savedTournoi = tournoiRepository.save(tournoiEntity);
        return modelMapper.map(savedTournoi, TournoiDto.class);
    }

    @Override
    public List<TournoiDtoRes> getAll() {
        List<Tournoi> tournoiList = tournoiRepository.findAll();
        return tournoiList.stream()
                .map(tournoi -> modelMapper.map(tournoi, TournoiDtoRes.class))
                .collect(Collectors.toList());
    }



}
