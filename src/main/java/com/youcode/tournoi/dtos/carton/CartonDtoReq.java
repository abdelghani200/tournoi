package com.youcode.tournoi.dtos.carton;

import com.youcode.tournoi.entities.Match;
import com.youcode.tournoi.entities.Player;
import com.youcode.tournoi.enums.TypeCarton;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class CartonDtoReq {
    private Long idCarton;
    private Match match;
    private Player player;
    private TypeCarton type;

}
