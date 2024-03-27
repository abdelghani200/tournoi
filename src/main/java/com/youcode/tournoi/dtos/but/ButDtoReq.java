package com.youcode.tournoi.dtos.but;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ButDtoReq {

    private Long idBut;
    private Integer numberOfGoal;
    private Long matchId;
    private Long equipeId;
    private Long playerId;
}
