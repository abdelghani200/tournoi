package com.youcode.tournoi.dtos.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AdminUDto extends UserDto {
    private String adress;
    private String phone;
}
