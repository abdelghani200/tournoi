package com.youcode.tournoi.dtos.equipe;

import com.youcode.tournoi.entities.Equipe;
import com.youcode.tournoi.entities.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EquipeWithPlayerDtoRes {

    private Long id;
    private Long idEquipe;
    private Long idUser;

}
