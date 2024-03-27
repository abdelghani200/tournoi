package com.youcode.tournoi.dtos.match;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class MatchFbDto {
    private Long idMatch;
    private Date dateMatch;
    private LocalTime heureMatch;
    private Long tournoiId;
    private Long equipe1Id;
    private Long equipe2Id;
    private int scoreEquipe1;
    private int scoreEquipe2;

}
