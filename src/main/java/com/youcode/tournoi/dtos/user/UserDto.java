package com.youcode.tournoi.dtos.user;

import com.youcode.tournoi.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String nomUser;
    private String prenomUser;
    private String email;
    private UserRole role;
}
