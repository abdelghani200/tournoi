package com.youcode.tournoi.dtos.player;

import com.youcode.tournoi.enums.TypePlayer;
import com.youcode.tournoi.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerDto {
    private Long idUser;
    private String nomUser;
    private String prenomUser;
    private String email;
    private UserRole role;
    private TypePlayer type;
    private String password;

    public PlayerDto(String nomUser) {
        this.nomUser = nomUser;
    }
}
