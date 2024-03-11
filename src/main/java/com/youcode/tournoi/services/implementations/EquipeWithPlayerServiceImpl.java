package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.dtos.equipe.EquipeWithPlayerDtoReq;
import com.youcode.tournoi.dtos.equipe.EquipeWithPlayerDtoRes;
import com.youcode.tournoi.dtos.player.PlayerDto;
import com.youcode.tournoi.dtos.player.PlayerDtoRes;
import com.youcode.tournoi.entities.Equipe;
import com.youcode.tournoi.entities.EquipeWithPlayer;
import com.youcode.tournoi.entities.Player;
import com.youcode.tournoi.exceptions.EquipeNotFoundException;
import com.youcode.tournoi.exceptions.PlayerAlreadyInTeamException;
import com.youcode.tournoi.persistence.EquipeRepository;
import com.youcode.tournoi.persistence.EquipeWithPlayerRepository;
import com.youcode.tournoi.services.interfaces.EquipeService;
import com.youcode.tournoi.services.interfaces.EquipeWithPlayerService;
import com.youcode.tournoi.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipeWithPlayerServiceImpl implements EquipeWithPlayerService {

    private final EquipeWithPlayerRepository equipeWithPlayerRepository;
    private final UserService userService;
    private final EquipeService equipeService;
    private final ModelMapper modelMapper;

    public EquipeWithPlayerServiceImpl(EquipeWithPlayerRepository equipeWithPlayerRepository, UserService userService, EquipeService equipeService, ModelMapper modelMapper) {
        this.equipeWithPlayerRepository = equipeWithPlayerRepository;
        this.userService = userService;
        this.equipeService = equipeService;
        this.modelMapper = modelMapper;
    }


    @Override
    public EquipeWithPlayerDtoRes savePlayerToEquipe(EquipeWithPlayerDtoRes withPlayer) {

        if (isPlayerAlreadyInTeamForTournament(withPlayer.getIdEquipe(), withPlayer.getIdTournoi())) {
            throw new PlayerAlreadyInTeamException("Le joueur est déjà inscrit dans une autre équipe de ce tournoi.");
        }

        EquipeWithPlayer equipeWithPlayer = modelMapper.map(withPlayer, EquipeWithPlayer.class);

        EquipeDtoRes equipe = equipeService.findById(withPlayer.getIdEquipe());
        PlayerDtoRes player = userService.findById(withPlayer.getIdUser());

        equipeWithPlayer.setEquipe(modelMapper.map(equipe, Equipe.class));
        equipeWithPlayer.setPlayer(modelMapper.map(player, Player.class));

        equipeWithPlayer = equipeWithPlayerRepository.save(equipeWithPlayer);

        return modelMapper.map(equipeWithPlayer, EquipeWithPlayerDtoRes.class);
    }

    @Override
    public List<EquipeWithPlayerDtoReq> getPlayersByEquipe(Long equipeId) {
        List<EquipeWithPlayer> equipeWithPlayers = equipeWithPlayerRepository.findByEquipeIdEquipe(equipeId);

        List<EquipeWithPlayerDtoReq> playersDtoRes = new ArrayList<>();

        for (EquipeWithPlayer equipeWithPlayer : equipeWithPlayers) {
            EquipeDtoRes equipeDtoRes = modelMapper.map(equipeWithPlayer.getEquipe(), EquipeDtoRes.class);
            PlayerDto playerDto = modelMapper.map(equipeWithPlayer.getPlayer(), PlayerDto.class);

            EquipeWithPlayerDtoReq equipeWithPlayerDtoReq = new EquipeWithPlayerDtoReq();
            equipeWithPlayerDtoReq.setId(equipeWithPlayer.getId());
            equipeWithPlayerDtoReq.setEquipe(equipeDtoRes);
            equipeWithPlayerDtoReq.setPlayer(playerDto);

            playersDtoRes.add(equipeWithPlayerDtoReq);
        }

        return playersDtoRes;
    }

    @Override
    public List<EquipeWithPlayerDtoReq> getPlayersByEquipes(Long equipeId1, Long equipeId2) {
        List<EquipeWithPlayerDtoReq> playersEquipe1 = getPlayersByEquipe(equipeId1);
        List<EquipeWithPlayerDtoReq> playersEquipe2 = getPlayersByEquipe(equipeId2);

        List<EquipeWithPlayerDtoReq> combinedPlayers = new ArrayList<>();
        combinedPlayers.addAll(playersEquipe1);
        combinedPlayers.addAll(playersEquipe2);

        return combinedPlayers;
    }



    public boolean isPlayerAlreadyInTeamForTournament(Long playerId, Long tournamentId) {
        List<EquipeWithPlayer> equipeWithPlayers = equipeWithPlayerRepository.findByPlayerIdAndTournoiId(playerId, tournamentId);
        return !equipeWithPlayers.isEmpty();
    }


}
