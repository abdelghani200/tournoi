package com.youcode.tournoi.dtos.but;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.dtos.match.MatchDto;
import com.youcode.tournoi.dtos.match.MatchDtoRes;
import com.youcode.tournoi.dtos.player.PlayerDto;
import com.youcode.tournoi.entities.Equipe;
import com.youcode.tournoi.entities.Match;
import com.youcode.tournoi.entities.Player;
import lombok.Data;

@Data
public class ButDtoRes {
    private Long idBut;
    private Integer numberOfGoal;
    private MatchDtoRes match;
    private EquipeDtoRes equipe;
    private PlayerDto player;
}
