package com.youcode.tournoi.dtos.equipe;

import com.youcode.tournoi.enums.TypeEquipe;
import lombok.Data;

@Data
public class EquipeDtoRes {
    private Long idEquipe;
    private String nomEquipe;
    private TypeEquipe type;
}
