package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.tournoi.FifaDto;
import com.youcode.tournoi.entities.Player;
import com.youcode.tournoi.entities.Tournoi;
import com.youcode.tournoi.exceptions.PlayerNotFoundException;
import com.youcode.tournoi.exceptions.TournoiNotFoundException;
import com.youcode.tournoi.persistence.PlayerRepository;
import com.youcode.tournoi.persistence.TournoiRepository;
import com.youcode.tournoi.services.interfaces.TournoiFifaService;
import org.springframework.stereotype.Service;

@Service
public class TournoiFifaServiceImpl implements TournoiFifaService {

    private final TournoiRepository tournoiRepository;
    private final PlayerRepository playerRepository;

    public TournoiFifaServiceImpl(TournoiRepository tournoiRepository, PlayerRepository playerRepository) {
        this.tournoiRepository = tournoiRepository;
        this.playerRepository = playerRepository;
    }


    @Override
    public FifaDto playerToFifa(FifaDto fifaDto) {
        Tournoi tournoi = tournoiRepository.findById(fifaDto.getTournoiId())
                .orElseThrow(() -> new TournoiNotFoundException("Tournoi FIFA non trouvé avec l'ID : " + fifaDto.getTournoiId()));

        Player player = playerRepository.findById(fifaDto.getPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("Joueur non trouvé avec l'ID : " + fifaDto.getPlayerId()));

        tournoiRepository.save(tournoi);

        return fifaDto;
    }


}
