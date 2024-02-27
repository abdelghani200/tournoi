package com.youcode.tournoi.services.implementations;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.dtos.equipe.EquipeWithPlayerDtoRes;
import com.youcode.tournoi.dtos.player.PlayerDtoRes;
import com.youcode.tournoi.entities.Equipe;
import com.youcode.tournoi.entities.EquipeWithPlayer;
import com.youcode.tournoi.entities.Player;
import com.youcode.tournoi.persistence.EquipeWithPlayerRepository;
import com.youcode.tournoi.services.interfaces.EquipeService;
import com.youcode.tournoi.services.interfaces.EquipeWithPlayerService;
import com.youcode.tournoi.services.interfaces.UserService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        EquipeWithPlayer equipeWithPlayer = modelMapper.map(withPlayer, EquipeWithPlayer.class);

        EquipeDtoRes equipe = equipeService.findById(withPlayer.getIdEquipe());
        PlayerDtoRes player = userService.findById(withPlayer.getIdUser());

        equipeWithPlayer.setEquipe(modelMapper.map(equipe, Equipe.class));
        equipeWithPlayer.setPlayer(modelMapper.map(player, Player.class));

        equipeWithPlayer = equipeWithPlayerRepository.save(equipeWithPlayer);

        return modelMapper.map(equipeWithPlayer, EquipeWithPlayerDtoRes.class);
    }
}
