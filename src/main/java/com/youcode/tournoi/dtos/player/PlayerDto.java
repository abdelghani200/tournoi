package com.youcode.tournoi.dtos.player;

import com.youcode.tournoi.enums.TypePlayer;
import com.youcode.tournoi.enums.UserRole;
import lombok.Data;

@Data
public class PlayerDto {
    private Long idUser;
    private String nomUser;
    private String prenomUser;
    private String email;
    private UserRole role;
    private TypePlayer type;
}
