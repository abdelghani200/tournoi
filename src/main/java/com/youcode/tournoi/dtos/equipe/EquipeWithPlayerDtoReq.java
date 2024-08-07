package com.youcode.tournoi.dtos.equipe;

import com.youcode.tournoi.dtos.player.PlayerDto;
import com.youcode.tournoi.dtos.player.PlayerDtoRes;
import com.youcode.tournoi.dtos.tournoi.TournoiDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EquipeWithPlayerDtoReq {
    private Long id;
    private EquipeDtoRes equipe;
    private PlayerDto player;
    private TournoiDto tournoi;

    private int nbMatchsJoues;
    private int points;
    private int nbMatchsGagnes;
    private int nbMatchsPerdus;
}
