package com.youcode.tournoi.dtos.match;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.dtos.player.PlayerDto;
import com.youcode.tournoi.dtos.player.PlayerDtoRes;
import com.youcode.tournoi.dtos.tournoi.TournoiDtoRes;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class MatchFbDtoRes {
    private Long idMatch;
    private Date dateMatch;
    private LocalTime heureMatch;
    private Integer scoreEquipe1;
    private Integer scoreEquipe2;
    private TournoiDtoRes tournoi;
    private EquipeDtoRes equipe1;
    private EquipeDtoRes equipe2;
    private PlayerDtoRes player1;
    private PlayerDtoRes player2;
}
