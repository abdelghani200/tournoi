package com.youcode.tournoi.dtos.player;

import com.youcode.tournoi.dtos.equipe.EquipeDtoRes;
import com.youcode.tournoi.enums.UserRole;
import lombok.Data;

@Data
public class PlayerDtoRes {
    private Long idUser;
    private String nomUser;
    private String prenomUser;
    private String email;
    private UserRole role;
}
