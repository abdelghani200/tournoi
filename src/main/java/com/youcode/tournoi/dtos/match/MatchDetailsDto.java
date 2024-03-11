package com.youcode.tournoi.dtos.match;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.dtos.player.PlayerDto;
import com.youcode.tournoi.dtos.tournoi.TournoiDtoRes;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class MatchDetailsDto {
    private Long idMatch;
    private Date dateMatch;
    private LocalTime heureMatch;
    private TournoiDtoRes tournoi;
    private EquipeDtoRes equipe1;
    private EquipeDtoRes equipe2;
    private List<PlayerDto> playersEquipe1;
    private List<PlayerDto> playersEquipe2;
}
