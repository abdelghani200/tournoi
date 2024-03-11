package com.youcode.tournoi.dtos.equipe;

import com.youcode.tournoi.enums.TypeEquipe;
import lombok.Data;

@Data
public class EquipeStatDto {
    private Long idEquipe;
    private String nomEquipe;
    private TypeEquipe type;
    private int nbMatchsJoues;
    private int points;
    private int nbMatchsGagnes;
    private int nbMatchsPerdus;
}
