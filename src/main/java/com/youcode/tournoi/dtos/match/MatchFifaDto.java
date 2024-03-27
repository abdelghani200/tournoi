package com.youcode.tournoi.dtos.match;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class MatchFifaDto {
    private Long idMatch;
    private Date dateMatch;
    private LocalTime heureMatch;
    private Long tournoiId;
    private Long player1Id;
    private Long player2Id;
}
