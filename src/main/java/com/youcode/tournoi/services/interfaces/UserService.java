package com.youcode.tournoi.services.interfaces;


import com.youcode.tournoi.dtos.player.PlayerDtoRes;
import com.youcode.tournoi.dtos.user.AdminDto;
import com.youcode.tournoi.dtos.player.PlayerDto;

import java.util.List;

public interface UserService {
    AdminDto createAdmin(AdminDto admin);
    PlayerDto createPlayer(PlayerDto player);

    List<PlayerDtoRes> getAllPlayers();

}
