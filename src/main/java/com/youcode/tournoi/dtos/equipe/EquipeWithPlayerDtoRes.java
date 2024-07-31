package com.youcode.tournoi.dtos.equipe;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EquipeWithPlayerDtoRes {

    private Long id;
    private Long idEquipe;
    private Long idTournoi;
    private Long idUser;

}
