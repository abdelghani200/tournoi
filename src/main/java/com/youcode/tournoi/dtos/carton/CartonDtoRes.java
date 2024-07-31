package com.youcode.tournoi.dtos.carton;

import com.youcode.tournoi.entities.Match;
import com.youcode.tournoi.entities.Player;
import com.youcode.tournoi.enums.TypeCarton;
import lombok.Data;

@Data
public class CartonDtoRes {
    private Long idCarton;
    private Long matchId;
    private Long playerId;
    private TypeCarton type;
}
