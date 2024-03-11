package com.youcode.tournoi.dtos.user;

import com.youcode.tournoi.enums.UserRole;
import lombok.Data;

@Data
public class AdminDto {
    private Long idUser;
    private String nomUser;
    private String prenomUser;
    private String email;
    private String password;
    private UserRole role;
    private String adress;
    private String phone;
}
