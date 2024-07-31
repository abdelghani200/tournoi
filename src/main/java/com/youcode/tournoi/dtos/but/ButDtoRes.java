package com.youcode.tournoi.dtos.but;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.dtos.match.MatchFbDtoRes;
import com.youcode.tournoi.dtos.player.PlayerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ButDtoRes {
    private Long idBut;
    private Integer numberOfGoal;
    private MatchFbDtoRes match;
    private EquipeDtoRes equipe;
    private PlayerDto player;
}
