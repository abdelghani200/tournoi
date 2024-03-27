package com.youcode.tournoi.dtos.user;

import com.youcode.tournoi.enums.TypePlayer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PlayerDto extends UserDto {
    private TypePlayer type;
}